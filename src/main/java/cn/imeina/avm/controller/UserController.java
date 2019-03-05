package cn.imeina.avm.controller;

import cn.imeina.avm.base.BaseController;
import cn.imeina.avm.constant.Constant;
import cn.imeina.avm.entity.ResponseData;
import cn.imeina.avm.entity.User;
import cn.imeina.avm.exception.ServiceException;
import cn.imeina.avm.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author gaopengfei
 */
@RequestMapping(path = "/user")
@Controller
@SessionAttributes(value = {"user"})
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    /**
     * 跳转到登录页
     *
     * @return login.html
     */
    @GetMapping(value = Constant.ActionPath.SIGN_IN)
    public ModelAndView signIn() {
        ModelAndView modelAndView = createModelAndView();
        modelAndView.setViewName(Constant.Page.LOGIN);
        return modelAndView;
    }

    /**
     * Account login
     *
     * @return account pass return index or login
     */
    @PostMapping(value = Constant.ActionPath.LOGIN)
    public @ResponseBody
    ResponseEntity<ResponseData> login(User loginUser, ModelMap modelMap) throws ServiceException {
        logger.debug("用户：" + loginUser.getUserName() + "登录...");
        User user = userService.login(loginUser);
        modelMap.put(Constant.KEY_USER, user);
        return ResponseEntity.ok(ResponseData.success(user));
    }

    /**
     * Account logout
     *
     * @return
     */
    @GetMapping(value = "logout")
    public String logout(SessionStatus sessionStatus) {
        logger.debug("退出登录...");
        userService.logOut();
        sessionStatus.setComplete();
        return redirect("/user/signIn");
    }

    /**
     * User register
     *
     * @param user user info
     * @return register success return true or false
     */
    @PutMapping(value = "/save")
    public @ResponseBody
    ResponseEntity save(@RequestBody User user) throws ServiceException {
        logger.debug("用户注册：" + user.toString());
        userService.save(user);
        return ResponseEntity.ok(ResponseData.success());
    }

    /**
     * Update User Info
     *
     * @param user
     * @return
     */
    @RequestMapping()
    public @ResponseBody
    ResponseEntity update(@RequestBody User user) {
        logger.debug("更新用户：" + user.toString());
        userService.update(user);
        return ResponseEntity.ok(ResponseData.success());
    }

    /**
     * Delete User
     *
     * @param uid userId
     * @return delete success return true or false
     */
    @DeleteMapping("/delete/{uid}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable("uid") String uid) {
        logger.debug("删除用户：" + uid);
        userService.delete(uid);
        return ResponseEntity.ok(ResponseData.success());
    }
}
