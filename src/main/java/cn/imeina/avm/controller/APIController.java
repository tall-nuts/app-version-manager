package cn.imeina.avm.controller;

import cn.hutool.core.util.StrUtil;
import cn.imeina.avm.constant.Constant;
import cn.imeina.avm.entity.App;
import cn.imeina.avm.entity.ResponseData;
import cn.imeina.avm.entity.User;
import cn.imeina.avm.exception.BusinessStatus;
import cn.imeina.avm.exception.ServiceException;
import cn.imeina.avm.service.IAppService;
import cn.imeina.avm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gaopengfei
 * API Controller for request, return json data.
 */
@RestController
@RequestMapping("/api/")
public class APIController {

    @Autowired
    @Qualifier(value = "UserService")
    IUserService userService;

    @Autowired
    @Qualifier(value = "AppService")
    IAppService appService;

    /**
     * 登录
     *
     * @param user 账号密码
     * @return
     */
    @PostMapping(Constant.ActionPath.LOGIN)
    public @ResponseBody
    ResponseEntity<ResponseData> login(@RequestBody User user) throws ServiceException {
        if (user == null || StrUtil.isEmpty(user.getUserName()) || StrUtil.isEmpty(user.getPassword())) {
            throw new ServiceException(BusinessStatus.PARAMETER_ERROR);
        } else {
            User loginUser = userService.login(user);
            return ResponseEntity.ok(ResponseData.success(loginUser));
        }
    }

    /**
     * Fetch all application info
     *
     * @return
     */
    @RequestMapping(Constant.ActionPath.APP_LIST)
    public ResponseEntity<ResponseData> fetchApplicationList() {
        List<App> appList = appService.findAll();
        Map<String, List<App>> map = new HashMap<>();
        map.put("list", appList);
        return ResponseEntity.ok(ResponseData.success(map));
    }

    /**
     * 检查更新
     *
     * @param os          应用类型：0安卓 1IOS
     * @param versionCode 当前应用版本号
     * @param packageName 应用包名
     * @return
     */
    @GetMapping(Constant.ActionPath.APP_CHECK_UPGRADE)
    public ResponseEntity<ResponseData> checkUpgrade(int os, long versionCode, String packageName) throws ServiceException {
        ResponseData responseData = ResponseData.success();
        App app = appService.findByPackageNameAndOS(packageName, os);
        if (app.getLastUpgradeInfo() == null
                || (app.getLastUpgradeInfo() != null
                && app.getLastUpgradeInfo().getVersionCode() <= versionCode)) {
            responseData.setMessage("暂无更新");
        } else {
            responseData.setData(app);
        }
        return ResponseEntity.ok(responseData);
    }
}