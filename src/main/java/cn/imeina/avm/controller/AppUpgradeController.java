package cn.imeina.avm.controller;

import cn.imeina.avm.constant.Constant;
import cn.imeina.avm.entity.AppUpgrade;
import cn.imeina.avm.entity.ResponseData;
import cn.imeina.avm.exception.ServiceException;
import cn.imeina.avm.service.IAppUpgradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author gaopengfei
 * App升级信息控制器
 */
@Controller
@RequestMapping("/appUpgrade")
public class AppUpgradeController {

    private static final Logger logger = LoggerFactory.getLogger(AppUpgradeController.class);

    @Autowired
    @Qualifier("UpgradeService")
    IAppUpgradeService appUpgradeService;

    /**
     * 更改应用状态
     *
     * @param appUpgrade appId及status
     * @return
     */
    @PutMapping(Constant.ActionPath.APP_UPGRADE_STATUS_UPDATE)
    public @ResponseBody
    ResponseEntity<ResponseData> appStatusUpdate(@RequestBody AppUpgrade appUpgrade) throws ServiceException {
        logger.debug("更改应用状态：" + appUpgrade.toString());
        appUpgradeService.update(appUpgrade);
        return ResponseEntity.ok(ResponseData.success());
    }

    /**
     * 发布更新
     *
     * @param appUpgrade 更新信息
     * @return
     */
    @PostMapping(Constant.ActionPath.APP_UPGRADE)
    public @ResponseBody
    ResponseEntity<ResponseData> appUpgrade(@RequestBody AppUpgrade appUpgrade) throws ServiceException {
        logger.debug("发布更新：" + appUpgrade.toString());
        appUpgradeService.add(appUpgrade);
        return ResponseEntity.ok(ResponseData.success());
    }
}
