package cn.imeina.avm.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.imeina.avm.dao.AppUpgradeDAO;
import cn.imeina.avm.exception.BusinessStatus;
import cn.imeina.avm.exception.ServiceException;
import cn.imeina.avm.entity.AppUpgrade;
import cn.imeina.avm.entity.Resource;
import cn.imeina.avm.service.IAppUpgradeService;
import cn.imeina.avm.service.IResourceService;
import cn.imeina.avm.util.OSSManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("UpgradeService")
public class AppUpgradeServiceImpl implements IAppUpgradeService {

    @Autowired
    AppUpgradeDAO appUpgradeDAO;

    @Autowired
    IResourceService resourceService;

    @Override
    public AppUpgrade selectLastUpgradeByAppId(String appId) {
        return appUpgradeDAO.selectLastUpgradeByAppId(appId);
    }

    @Override
    public List<AppUpgrade> findAppUpgradeListByAppId(String appId) {
        return appUpgradeDAO.selectAppUpgradeByAppId(appId);
    }

    @Override
    public List<AppUpgrade> findAll() {
        return appUpgradeDAO.selectAll();
    }

    @Override
    public boolean add(AppUpgrade appUpgrade) throws ServiceException {
        Date date = new Date();
        appUpgrade.setId(IdUtil.simpleUUID());
        appUpgrade.setCreateTime(date);
        appUpgrade.setUpdateTime(date);
        Resource resource = resourceService.findById(appUpgrade.getResourceId());
        appUpgrade.setResource(resource);
        int upgradeStatus = appUpgradeDAO.save(appUpgrade);
        if (upgradeStatus <= 0) {
            deleteResource(appUpgrade);
        }
        return true;
    }

    /**
     * 删除OSS资源文件
     * @param appUpgrade 版本更新信息
     * @throws ServiceException
     */
    private void deleteResource(AppUpgrade appUpgrade) throws ServiceException {
        // 删除资源
        Resource resource = appUpgrade.getResource();
        if (resource != null) {
            if (resource.getMimeType().contains("image/")) {
                OSSManager.getInstance().deleteImageResource(resource.getObjectKey());
            } else {
                OSSManager.getInstance().deleteOtherResource(resource.getObjectKey());
            }
        }
        throw new ServiceException(BusinessStatus.FAILURE);
    }

    @Override
    public boolean update(AppUpgrade appUpgrade) throws ServiceException {
        int updateStatus = appUpgradeDAO.update(appUpgrade);
        if (updateStatus <= 0) {
            deleteResource(appUpgrade);
        }
        return true;
    }

    @Override
    public boolean delete(String appId) {
        // 查询该应用对应的升级记录列表
        List<AppUpgrade> upgradeList = findAppUpgradeListByAppId(appId);
        List<String> resIds = new ArrayList<>();
        if (upgradeList != null && !upgradeList.isEmpty()) {
            for (AppUpgrade appUpgrade : upgradeList) {
                // 封装资源id集合
                resIds.add(appUpgrade.getResourceId());
            }
        }
        // 删除升级记录
        appUpgradeDAO.delete(appId);
        if (!resIds.isEmpty()) {
            resourceService.deleteList(resIds);
        }
        return true;
    }
}
