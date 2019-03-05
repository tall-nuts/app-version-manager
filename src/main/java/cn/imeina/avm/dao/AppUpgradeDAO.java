package cn.imeina.avm.dao;

import cn.imeina.avm.entity.AppUpgrade;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 应用升级操作
 */
@Mapper
public interface AppUpgradeDAO {

    /**
     * 添加升级信息
     * @param appUpgrade 升级信息
     */
    int save(AppUpgrade appUpgrade);

    /**
     * 更新升级信息
     * @param appUpgrade 升级信息
     */
    int update(AppUpgrade appUpgrade);

    /**
     * 查询最新版本信息
     * @param appId 应用ID
     * @return
     */
    AppUpgrade selectLastUpgradeByAppId(String appId);
    /**
     * 查询App升级信息通过应用ID
     * @param appId 应用ID
     * @return
     */
    List<AppUpgrade> selectAppUpgradeByAppId(String appId);

    /**
     * 查询全部记录
     * @return
     */
    List<AppUpgrade> selectAll();
    /**
     * 通过ID删除升级记录
     * @param appId ID
     */
    int delete(String appId);
}
