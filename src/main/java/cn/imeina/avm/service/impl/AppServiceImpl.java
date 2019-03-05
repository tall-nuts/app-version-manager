package cn.imeina.avm.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.imeina.avm.dao.AppDAO;
import cn.imeina.avm.exception.BusinessStatus;
import cn.imeina.avm.exception.ServiceException;
import cn.imeina.avm.entity.App;
import cn.imeina.avm.service.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Apk业务处理
 */
@Service("AppService")
public class AppServiceImpl implements IAppService {

    @Autowired
    AppDAO appDao;

    @Override
    public App findById(String id) {
        return appDao.selectById(id);
    }

    @Override
    public App findByPackageNameAndOS(String packageName, int os) throws ServiceException {
        if (StrUtil.isEmpty(packageName)) {
            throw new ServiceException(BusinessStatus.PARAMETER_ERROR);
        }
        App app = appDao.selectByPackageName(packageName, os);
        if (app == null) {
            throw new ServiceException(BusinessStatus.APP_NOT_EXIST);
        }
        return app;
    }

    @Override
    public List<App> findAll() {
        return appDao.selectAll();
    }

    @Override
    public boolean add(App app) throws ServiceException {
        App existApp = appDao.selectByPackageName(app.getPackageName(), app.getOs());
        if (existApp != null) {
            throw new ServiceException(BusinessStatus.APP_EXIST);
        }
        app.setId(IdUtil.simpleUUID());
        DateTime date = new DateTime();
        app.setCreateTime(date);
        app.setUpdateTime(date);
        // TODO 获取当前登录用户的id作为应用创建人
        app.setCreateUid("ec5f0fdafead48f398c0a530eab8520d");
        int saveStatus = appDao.save(app);
        if (saveStatus <= 0) {
            throw new ServiceException(BusinessStatus.FAILURE);
        }
        return true;
    }

    @Override
    public boolean update(App app) throws ServiceException {
        app.setUpdateTime(new DateTime());
        int updateStatus = appDao.update(app);
        if (updateStatus <= 0) {
            throw new ServiceException(BusinessStatus.FAILURE);
        }
        return true;
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        int deleteStatus = appDao.delete(id);
        if (deleteStatus <= 0) {
            throw new ServiceException(BusinessStatus.FAILURE);
        }
        return true;
    }
}
