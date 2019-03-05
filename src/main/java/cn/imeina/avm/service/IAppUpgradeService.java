package cn.imeina.avm.service;

import cn.imeina.avm.exception.ServiceException;
import cn.imeina.avm.entity.AppUpgrade;

import java.util.List;

public interface IAppUpgradeService {

    /**
     * 查询最新版本信息
     * @param appId 应用ID
     * @return
     */
    AppUpgrade selectLastUpgradeByAppId(String appId);
    /**
     * 通过应用ID查询升级记录
     *
     * @param appId 应用ID
     * @return
     */
    List<AppUpgrade> findAppUpgradeListByAppId(String appId);

    /**
     * 查询全部升级记录
     *
     * @return
     */
    List<AppUpgrade> findAll();

    /**
     * 添加升级记录
     *
     * @param appUpgrade 升级信息
     */
    boolean add(AppUpgrade appUpgrade) throws ServiceException;

    /**
     * 修改升级信息
     *
     * @param appUpgrade 升级信息
     */
    boolean update(AppUpgrade appUpgrade) throws ServiceException;

    /**
     * 删除升级信息
     *
     * @param appId ID
     */
    boolean delete(String appId);
}
