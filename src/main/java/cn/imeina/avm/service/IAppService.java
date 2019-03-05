package cn.imeina.avm.service;

import cn.imeina.avm.exception.ServiceException;
import cn.imeina.avm.entity.App;

import java.util.List;

public interface IAppService {

    /**
     * query apkInfo by id
     *
     * @param id 主键
     * @return
     */
    App findById(String id);

    /**
     * query apkInfo by packageName
     * @param packageName
     * @return
     */
    App findByPackageNameAndOS(String packageName, int os) throws ServiceException;

    /**
     * query all akpInfo
     * @return
     */
    List<App> findAll();

    /**
     * add akpInfo
     * @param app data
     */
    boolean add(App app) throws ServiceException;

    /**
     * update akpInfo
     * @param app data
     */
    boolean update(App app) throws ServiceException;

    /**
     * delete apkInfo by id
     * @param id 主键
     */
    boolean delete(String id) throws ServiceException;
}
