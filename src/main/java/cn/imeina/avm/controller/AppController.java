package cn.imeina.avm.controller;

import cn.imeina.avm.base.BaseController;
import cn.imeina.avm.constant.Constant;
import cn.imeina.avm.entity.App;
import cn.imeina.avm.entity.ResponseData;
import cn.imeina.avm.exception.ServiceException;
import cn.imeina.avm.service.IAppService;
import cn.imeina.avm.service.IAppUpgradeService;
import cn.imeina.avm.service.IResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * @author gaopengfei
 * 应用管理控制器
 */
@Controller
@RequestMapping("/app")
public class AppController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    @Qualifier(value = "AppService")
    IAppService appService;
    @Autowired
    @Qualifier(value = "ResourceService")
    IResourceService resourceService;
    @Autowired
    @Qualifier(value = "UpgradeService")
    IAppUpgradeService appUpgradeService;

    /**
     * 动态跳转页面
     *
     * @param pageName 物理视图名称
     * @return
     */
    @RequestMapping(value = "/{pageName}")
    public String pageAction(@PathVariable String pageName) {
        return "fragment/" + pageName;
    }

    /**
     * 获取应用列表
     *
     * @return
     */
    @GetMapping(Constant.ActionPath.APP_LIST)
    public ModelAndView fetchAppList() {
        ModelAndView modelAndView = createModelAndView();
        modelAndView.setViewName("fragment/app-list");
        List<App> appList = appService.findAll();
        modelAndView.addObject("appList", appList);
        return modelAndView;
    }

    /**
     * 创建应用
     *
     * @param app 应用信息
     * @return
     */
    @PostMapping(Constant.ActionPath.APP_CREATE)
    public @ResponseBody
    ResponseEntity<ResponseData> createApplication(@RequestBody App app) throws ServiceException {
        logger.debug("创建应用：" + app.toString());
        appService.add(app);
        return ResponseEntity.ok(ResponseData.success());
    }

    /**
     * 修改应用
     *
     * @param app 应用信息
     * @return
     */
    @PutMapping(Constant.ActionPath.APP_UPDATE)
    public @ResponseBody
    ResponseEntity<ResponseData> updateApplication(@RequestBody App app) throws ServiceException {
        logger.debug("修改应用：" + app.toString());
        appService.update(app);
        return ResponseEntity.ok(ResponseData.success());
    }

    /**
     * 删除应用
     *
     * @param appId 应用ID
     * @return
     */
    @Transactional
    @DeleteMapping(Constant.ActionPath.APP_DELETE)
    public @ResponseBody
    ResponseEntity<ResponseData> deleteApplication(@PathVariable("appId") String appId) throws ServiceException {
        logger.debug("删除应用：" + appId);
        // 删除应用
        appService.delete(appId);
        // 删除该应用升级记录
        appUpgradeService.delete(appId);
        return ResponseEntity.ok(ResponseData.success());
    }

    /**
     * 文件上传
     * 如果上传的为.apk，则参考{@link "https://github.com/hsiafan/apk-parser"}动态解析apk获取应用信息
     *
     * @param file 图片、.apk、.ipa
     * @return
     */
    @PostMapping("/upload")
    public @ResponseBody
    ResponseEntity<ResponseData> upload(@RequestParam("file") MultipartFile file) throws ServiceException {
        ResponseData responseData = resourceService.uploadFile(file);
        return ResponseEntity.ok(responseData);
    }
}
