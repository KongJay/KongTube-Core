package com.jaychou.admin.controller;


import com.jaychou.component.RedisComponent;
import com.jaychou.config.AppConfig;
import com.jaychou.entity.vo.ResponseVO;
import com.jaychou.exception.BusinessException;
import com.jaychou.utils.StringTools;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息表 Controller
 */
@RestController
@RequestMapping("/account")
@Validated
public class AccountController extends ABaseController {

    @Resource
    private RedisComponent redisComponent;
    @Resource
    private AppConfig appConfig;

    @RequestMapping("/checkCode")
    public ResponseVO checkCode() {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(100, 42);
        String code = captcha.text();
        String checkCodeKey = redisComponent.saveCheckCode(code);
        String checkCodebase64 = captcha.toBase64();
        Map<String, String> result = new HashMap<>();
        result.put("checkCodebase64", checkCodebase64);
        result.put("checkCodeKey", checkCodeKey);
        return getSuccessResponseVO(result);
    }


    @RequestMapping("/login")
    public ResponseVO login(HttpServletResponse httpServletResponse,
                            @NotEmpty String account,
                            @NotEmpty String password,
                            @NotEmpty String checkCodeKey,
                            @NotEmpty String checkCode) {
        try {
            if (!checkCode.equalsIgnoreCase(redisComponent.getCheckCode(checkCodeKey))) {
                throw new BusinessException("图片验证码不正确");
            }
            if (!account.equals(appConfig.getAccount()) || !password.equals(StringTools.encodeByMd5(appConfig.getPassword()))) {
                throw new BusinessException("账号或密码不正确");
            }
            String token = redisComponent.saveTokenInfo4Admin(account);
            saveToken2Cookies(httpServletResponse, token);
            return getSuccessResponseVO(account);
        } finally {
            redisComponent.cleanCheckCode(checkCodeKey);
        }
    }
    @RequestMapping("/logout")
    public ResponseVO logout(HttpServletResponse httpServletResponse) {
        cleanCookies(httpServletResponse);
        return getSuccessResponseVO(null);
    }
}