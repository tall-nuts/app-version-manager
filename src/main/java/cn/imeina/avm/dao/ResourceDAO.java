package cn.imeina.avm.dao;

import cn.imeina.avm.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ResourceDAO {

    /**
     * 通过ID获取资源
     * @param id 主键
     * @return
     */
    Resource selectById(String id);

    /**
     * 查询所有资源
     * @return
     */
    List<Resource> selectAll();

    /**
     * 添加资源
     * @param resource data
     */
    int save(Resource resource);

    /**
     * 删除资源
     * @param id 资源ID
     */
    int delete(String id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteList(List<String> ids);
}
