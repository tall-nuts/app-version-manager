package cn.imeina.avm.base;

import cn.imeina.avm.constant.Constant;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器基类
 */
public class BaseController {

    /**
     * Create ModelAndView instance
     * @return ModelAndView
     */
    public ModelAndView createModelAndView(){
        return new ModelAndView();
    }

    /**
     * Get HttpServletRequest
     * @return request
     */
    public HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * Redirect
     * @param actionPath {@link Constant.ActionPath}
     * @return
     */
    public String redirect(String actionPath){
        return "redirect:" + actionPath;
    }
}
