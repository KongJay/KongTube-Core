package com.jaychou.admin.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * ClassName: WebAppConfigurer
 * Package: com.jaychou.admin.interceptor
 * Description:
 *
 * @Author: 红模仿
 * @Create: 2025/5/13 - 16:46
 * @Version: v1.0
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer{
    @Resource
    private AppInterceptor appInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appInterceptor).addPathPatterns("/**");
    }

}
