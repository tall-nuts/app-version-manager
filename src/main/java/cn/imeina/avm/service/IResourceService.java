package cn.imeina.avm.service;

import cn.imeina.avm.exception.ServiceException;
import cn.imeina.avm.entity.Resource;
import cn.imeina.avm.entity.ResponseData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IResourceService {
    /**
     * query resource by id
     *
     * @param id 主键
     * @return
     */
    Resource findById(String id);

    /**
     * query all resource
     * @return
     */
    List<Resource> findAll();

    /**
     * add resource
     * @param resource data
     */
    boolean add(Resource resource);

    /**
     * delete resource by id
     * @param id 主键
     */
    boolean delete(String id);

    /**
     * 批量删除资源
     * @param ids 资源ID集合
     * @return
     */
    boolean deleteList(List<String> ids);

    /**
     * 上传资源
     * @param file 文件
     * @return
     */
    ResponseData uploadFile(MultipartFile file) throws ServiceException;
}
