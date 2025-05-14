package com.jaychou.admin.interceptor;

import com.jaychou.component.RedisComponent;
import com.jaychou.entity.constants.Constants;
import com.jaychou.entity.enums.ResponseCodeEnum;
import com.jaychou.exception.BusinessException;
import com.jaychou.utils.StringTools;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: AppInterceptor
 * Package: com.jaychou.admin.interceptor
 * Description:
 *
 * @Author: 红模仿
 * @Create: 2025/5/13 - 16:50
 * @Version: v1.0
 */
@Component
public class AppInterceptor implements HandlerInterceptor {
    private final static String URL_ACCOUNT = "/account";
    private final static String URL_FILE = "/file";
    @Resource
    private RedisComponent redisComponent;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         if(handler == null){
         return false;
         }
         if(handler instanceof HandlerMethod){
             return true;
         }
         if(request.getRequestURI().contains(URL_ACCOUNT)){
             return true;
         }
         String token = request.getHeader(Constants.TOKEN_ADMIN);
         if(request.getRequestURI().contains(URL_FILE)){
             token = getTokenFromCookie(request);
         }
         if(StringTools.isEmpty(token)){
                throw new BusinessException(ResponseCodeEnum.CODE_901);
         }
         Object sessionObj = redisComponent.getTokenInfo4Admin(token);
         if(sessionObj == null){
             throw new BusinessException(ResponseCodeEnum.CODE_901);
         }
         return true;
    }
    private String getTokenFromCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        String token = null;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(Constants.TOKEN_ADMIN)){
                return cookie.getValue();
            }
        }
        return null;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
