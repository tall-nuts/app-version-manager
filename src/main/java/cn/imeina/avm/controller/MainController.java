package cn.imeina.avm.controller;

import cn.imeina.avm.base.BaseController;
import cn.imeina.avm.constant.Constant;
import cn.imeina.avm.service.IAppService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author gaopengfei created by 2018/11/26
 */

@Controller
public class MainController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(MainController.class);

    @Autowired
    @Qualifier("AppService")
    IAppService appService;

    @GetMapping(Constant.ActionPath.INDEX)
    public String index() {
        return Constant.Page.INDEX;
    }
}