package cn.imeina.avm.dao;

import cn.imeina.avm.entity.App;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 应用操作
 */
@Mapper
public interface AppDAO {
    /**
     * query apkInfo by id
     * @param id 主键
     * @return
     */
    App selectById(String id);

    /**
     * query all akpInfo
     * @return
     */
    List<App> selectAll();

    /**
     * add akpInfo
     * @param app data
     */
    int save(App app);

    /**
     * update akpInfo
     * @param app data
     */
    int update(App app);

    /**
     * delete apkInfo by id
     * @param id 主键
     */
    int delete(String id);

    /**
     * query apkInfo by packageName
     * @param packageName
     * @return
     */
    App selectByPackageName(@Param("packageName") String packageName, @Param("os") int os);
}
