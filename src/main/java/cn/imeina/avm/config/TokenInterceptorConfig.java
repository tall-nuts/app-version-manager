package cn.imeina.avm.config;

import cn.imeina.avm.interceptor.TokenHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author gaopengfei
 * @date 2019-02-28
 * @description 授权拦截配置
 */
@Configuration
public class TokenInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/api", "/user/signIn", "/user/login","/js/**", "/css/**", "/fonts/**",
                        "/font-awesome/**", "/images/**", "/swf/**");
    }
}