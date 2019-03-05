package cn.imeina.avm.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.imeina.avm.controller.AppController;
import cn.imeina.avm.dao.ResourceDAO;
import cn.imeina.avm.exception.BusinessStatus;
import cn.imeina.avm.exception.ServiceException;
import cn.imeina.avm.entity.Resource;
import cn.imeina.avm.entity.ResponseData;
import cn.imeina.avm.service.IResourceService;
import cn.imeina.avm.util.EncryptUtils;
import cn.imeina.avm.util.IpaUtil;
import cn.imeina.avm.util.OSSManager;
import cn.imeina.avm.util.StringUtils;
import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Resource业务处理
 *
 * @author gaopengfei
 */
@Service("ResourceService")
public class ResourceServiceImpl implements IResourceService {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);
    /**
     * Android应用MIME
     */
    private static final String MIME_APK = "application/vnd.android.package-archive";
    /**
     * Iphone应用MIME
     */
    private static final String MIME_IPA = "application/octet-stream";

    @Autowired
    ResourceDAO resourceDAO;

    @Autowired
    MultipartConfigElement multipartConfigElement;

    @Override
    public Resource findById(String id) {
        return resourceDAO.selectById(id);
    }

    @Override
    public List<Resource> findAll() {
        return resourceDAO.selectAll();
    }

    @Override
    public boolean add(Resource resource) {
        return resourceDAO.save(resource) > 0;
    }

    @Override
    public boolean delete(String id) {
        return resourceDAO.delete(id) > 0;
    }

    @Override
    public boolean deleteList(List<String> ids) {
        return false;
    }

    @Override
    public ResponseData uploadFile(MultipartFile file) throws ServiceException {
        logger.debug("上传文件：" + file.toString());
        ResponseData responseData = ResponseData.success();
        if (!file.isEmpty()) {
            // 获取上传文件名
            String filename = file.getOriginalFilename();
            // 获取上传文件MIME
            String contentType = file.getContentType();
            // 获取本地临时存储目录(按照时间戳作为临时文件夹，防止重名文件覆盖及并发操作)
            String tempAbsDir = multipartConfigElement.getLocation() + File.separator + System.currentTimeMillis();
            File tempDir = new File(tempAbsDir);
            if (!tempDir.exists()) {
                tempDir.mkdirs();
            }
            // 创建本地临时文件
            File targetFile = new File(tempDir + File.separator + filename);
            try {
                file.transferTo(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 资源入库
            Resource resource = saveResource(targetFile, contentType);
            if (resource == null) {
                throw new ServiceException(BusinessStatus.FILE_UPLOAD_FAILURE);
            } else {
                Map<String, Object> map = new HashMap<>();
                if (MIME_APK.equals(contentType)) {
                    try {
                        // 获取Apk内的信息
                        ApkFile apkFile = new ApkFile(targetFile);
                        ApkMeta apkMeta = apkFile.getApkMeta();
                        map.put("packageName", apkMeta.getPackageName());
                        map.put("versionName", apkMeta.getVersionName());
                        map.put("versionCode", apkMeta.getVersionCode());
                        map.put("fileMD5", EncryptUtils.encryptMD5File2String(targetFile));
                        map.put("resourceId", resource.getId());
                        if (!StringUtils.isEmpty(resource.getUrl())) {
                            responseData.setData(map);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new ServiceException(BusinessStatus.FILE_PARSE_ERROR);
                    } finally {
                        if (targetFile.exists()) {
                            targetFile.delete();
                        }
                    }
                } else if (contentType.contains("image/")) {
                    map.put("image", resource.getUrl());
                    responseData.setData(map);
                } else if (MIME_IPA.equals(contentType)) {
                    Map<String, Object> ipaMap = IpaUtil.readIPA(targetFile);
                    ipaMap.put("fileMD5", EncryptUtils.encryptMD5File2String(targetFile));
                    ipaMap.put("resourceId", resource.getId());
                    responseData.setData(ipaMap);
                } else {
                    throw new ServiceException(BusinessStatus.FILE_FORMAT_ERROR);
                }
            }
        }
        return responseData;
    }


    /**
     * OSS上传及保存资源入库
     *
     * @param targetFile  本地文件
     * @param contentType 文件MIME类型
     * @return
     */
    private Resource saveResource(File targetFile, String contentType) {
        String[] arr = targetFile.getPath().split("\\.");
        String extension = "." + arr[arr.length - 1];
        // OSS Upload...
        String objectKey = generateObjectKey(targetFile);
        String fileOSSUrl = "";
        if (contentType.contains("image/")) {
            fileOSSUrl = OSSManager.getInstance().uploadImage(objectKey, targetFile);
        } else {
            fileOSSUrl = OSSManager.getInstance().uploadFile(objectKey, targetFile);
        }
        deleteTempFile(targetFile);
        if (StringUtils.isEmpty(fileOSSUrl)) {
            return null;
        }
        // 保存资源入库
        Resource resource = new Resource();
        resource.setId(IdUtil.simpleUUID());
        resource.setName(targetFile.getName());
        resource.setObjectKey(objectKey);
        resource.setUrl(fileOSSUrl);
        resource.setMimeType(contentType);
        resource.setSize(targetFile.length());
        resource.setExtension(extension);
        resource.setMd5(EncryptUtils.encryptMD5File2String(targetFile));
        resource.setCreateTime(new DateTime());
        add(resource);
        deleteTempFile(targetFile);
        return resource;
    }

    /**
     * 删除临时文件及其目录
     *
     * @param tempFile 临时文件
     */
    private void deleteTempFile(File tempFile) {
        tempFile.deleteOnExit();
        FileUtil.del(tempFile.getParent());
    }

    /**
     * 生成OSS图片资源ObjectKey
     *
     * @param file 文件
     * @return
     */
    private String generateObjectKey(File file) {
        logger.debug(file.getName() + "," + file.getPath());
        String extension = file.getName();
        if (extension.contains(".")) {
            String[] arr = extension.split("\\.");
            extension = "." + arr[arr.length - 1];
        }
        String fileMd5 = EncryptUtils.encryptMD5File2String(file);
        return EncryptUtils.encryptMD5ToString(fileMd5 + System.currentTimeMillis()) + extension;
    }
}
