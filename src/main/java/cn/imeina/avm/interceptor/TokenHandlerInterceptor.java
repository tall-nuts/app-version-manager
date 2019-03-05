package cn.imeina.avm.interceptor;

import cn.imeina.avm.entity.User;
import cn.imeina.avm.util.JWTUtil;
import cn.imeina.avm.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录过滤，权限验证
 *
 * @author gaopengfei
 */

public class TokenHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(TokenHandlerInterceptor.class);
    /**
     * 忽略校验的路径及资源
     */
    private static final String[] IGNORE_PATH = {"/api", "/user/signIn", "/user/login"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        User user = (User) request.getSession().getAttribute("user");
        String token = "";
        // 过滤无须验证的路径及资源
        for (String ignorePath : IGNORE_PATH) {
            if (requestURI.contains(ignorePath)) {
                return true;
            }
        }
        // 优先获取Header中的Authorization
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authorization)) {
            if (authorization.contains("Bearer ")) {
                token = authorization.replace("Bearer ", "");
            } else {
                token = authorization;
            }
            logger.debug("Header Authorization Token:" + token);
            boolean tokenValid = JWTUtil.verifyJWT(token);
            if (tokenValid && user != null) {
                return true;
            }
        } else {
            // Header中没有则从Cookie获取Token
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length != 0) {
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    if ("access_token".equals(name)) {
                        token = cookie.getValue();
                        logger.debug("Cookie AccessToken:" + token);
                        boolean tokenValid = JWTUtil.verifyJWT(token);
                        if (tokenValid && user != null) {
                            return true;
                        }
                    }
                }
            }
        }
        logger.debug("Auth failure...");
        response.sendRedirect(request.getContextPath() + "/user/signIn");
        return false;
    }
}