package cn.imeina.avm.util;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.CompleteMultipartUploadResult;
import com.aliyun.oss.model.UploadFileRequest;
import com.aliyun.oss.model.UploadFileResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.net.URL;
import java.util.Date;

/**
 * @author gaopengfei
 * 阿里云OSS对象存储工具类
 */
public class OSSManager {

    private static final Logger LOGGER = LogManager.getLogger(OSSManager.class);

    private static OSSManager instance;
    /**
     * 地域节点
     */
    private static final String ENDPOINT = "oss-cn-beijing.aliyuncs.com";
    /**
     * AccessKeyId
     */
    private static final String ACCESS_KEY_ID = "AccessKeyId";
    /**
     * AccessKeySecret
     */
    private static final String ACCESS_KEY_SECRET = "AccessKeySecret";
    /**
    /**
     * Bucket image
     */
    private static final String BUCKET_IMAGE = "oss--image";
    /**
     * Bucket other
     */
    private static final String BUCKET_OTHER = "oss--other";
    /**
     * User getOSSClient() get the OSSClient instance.
     */
    private OSSClient ossClient;

    private OSSManager() {
        initOSSClient();
    }

    public static OSSManager getInstance() {
        if (instance == null) {
            synchronized (OSSManager.class) {
                if (instance == null) {
                    instance = new OSSManager();
                }
            }
        }
        return instance;
    }

    private void initOSSClient() {
        // 创建ClientConfiguration。ClientConfiguration是OSSClient的配置类，可配置代理、连接超时、最大连接数等参数。
        ClientConfiguration conf = new ClientConfiguration();
        // 创建OSSClient实例。
        ossClient = new OSSClient(ENDPOINT, new DefaultCredentialProvider(ACCESS_KEY_ID, ACCESS_KEY_SECRET), conf);
    }

    private OSSClient getOSSClient() {
        if (ossClient == null) {
            initOSSClient();
        }
        return ossClient;
    }

    private void closeOSSClient() {
        if (ossClient != null) {
            ossClient.shutdown();
            ossClient = null;
        }
    }

    /**
     * 上传图片资源
     *
     * @param key       对象名
     * @param imageFile 文件
     */
    public String uploadImage(String key, File imageFile) {
        return upload(BUCKET_IMAGE, key, imageFile.getPath());
    }

    /**
     * 上传其他文件
     *
     * @param key  对象名
     * @param file 文件
     */
    public String uploadFile(String key, File file) {
        return upload(BUCKET_OTHER, key, file.getPath());
    }

    /**
     * 生成授权访问链接
     * @param bucketName BucketName
     * @param objectName 对象名称
     * @return
     */
    public URL generatePresignedUrl(String bucketName, String objectName) {
        // 设置URL过期时间为1小时。
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = getOSSClient().generatePresignedUrl(bucketName, objectName, expiration);
        closeOSSClient();
        return url;
    }

    /**
     * OSSClient Upload
     *
     * @param bucketName BucketName
     * @param key        ObjectKey
     * @param uploadFile File Local Path
     * @return if upload success return oss access url or null.
     */
    private String upload(String bucketName, String key, String uploadFile) {
        try {
            UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, key);
            // The local file to upload---it must exist.
            uploadFileRequest.setUploadFile(uploadFile);
            // Sets the concurrent upload task number to 5.
            uploadFileRequest.setTaskNum(5);
            // Sets the part size to 1MB.
            uploadFileRequest.setPartSize(1024 * 1024 * 1);
            // Enables the checkpoint file. By default it's off.
            uploadFileRequest.setEnableCheckpoint(true);
            UploadFileResult uploadResult = getOSSClient().uploadFile(uploadFileRequest);
            CompleteMultipartUploadResult multipartUploadResult =
                    uploadResult.getMultipartUploadResult();
            LOGGER.debug(multipartUploadResult.getETag());
            return generateOSSAccessUrl(bucketName, key);
        } catch (OSSException oe) {

            LOGGER.debug("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            LOGGER.debug("Error Message: " + oe.getErrorMessage());
            LOGGER.debug("Error Code:       " + oe.getErrorCode());
            LOGGER.debug("Request ID:      " + oe.getRequestId());
            LOGGER.debug("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            LOGGER.debug("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            LOGGER.debug("Error Message: " + ce.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            closeOSSClient();
        }
        return null;
    }

    /**
     * 删除图片资源
     * @param objectKey 资源ID
     */
    public void deleteImageResource(String objectKey){
        delete(BUCKET_IMAGE, objectKey);
    }

    /**
     * 删除其他资源
     * @param objectKey 资源ID
     */
    public void deleteOtherResource(String objectKey){
        delete(BUCKET_OTHER, objectKey);
    }

    /**
     * 删除资源
     * @param bucketName 存储区域
     * @param objectKey 资源Key
     */
    private void delete(String bucketName, String objectKey){
        getOSSClient().deleteObject(bucketName, objectKey);
    }

    /**
     * 生成上传后OSS文件访问地址
     * @param bucketName BucketName
     * @param objectKey ObjectKey
     * @return url
     */
    private String generateOSSAccessUrl(String bucketName, String objectKey){
        return "http://" + bucketName + "." + ENDPOINT + "/" + objectKey;
    }
}
