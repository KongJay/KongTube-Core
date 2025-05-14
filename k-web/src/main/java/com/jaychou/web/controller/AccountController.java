package com.jaychou.web.controller;


import com.jaychou.component.RedisComponent;
import com.jaychou.entity.constants.Constants;
import com.jaychou.entity.dto.TokenUserInfoDto;
import com.jaychou.entity.vo.ResponseVO;
import com.jaychou.exception.BusinessException;
import com.jaychou.service.UserInfoService;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    private UserInfoService userInfoService;
    @Resource
    private RedisComponent redisComponent;

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

    @RequestMapping("/register")
    public ResponseVO register(@NotEmpty @Email @Size(max = 150) String email,
                               @NotEmpty @Size(max = 20) String nickName,
                               @NotEmpty @Pattern(regexp = Constants.REGEX_PASSWORD) String registerPassword,
                               @NotEmpty String checkCodeKey,
                               @NotEmpty String checkCode) {
        try {
            String checkCode1 = redisComponent.getCheckCode(checkCodeKey);
            System.out.println(checkCode1);
            if(!checkCode.equalsIgnoreCase(checkCode1)){
                throw new BusinessException("图片验证码不正确");
            }
            userInfoService.register(email, nickName, registerPassword);
            return getSuccessResponseVO(null);
        }finally {
            redisComponent.cleanCheckCode(checkCodeKey);
        }
    }
    @RequestMapping("/login")
    public ResponseVO login(HttpServletResponse httpServletResponse,
                            @NotEmpty @Email String email,
                            @NotEmpty String password,
                            @NotEmpty String checkCodeKey,
                            @NotEmpty String checkCode) {
        try {
            if(!checkCode.equalsIgnoreCase(redisComponent.getCheckCode(checkCodeKey))){
                throw new BusinessException("图片验证码不正确");
            }
            String ipAddr = getIpAddr();
         TokenUserInfoDto tokenUserInfoDto =  userInfoService.login(email, password,ipAddr);
         saveToken2Cookies(httpServletResponse,tokenUserInfoDto.getToken());
            return getSuccessResponseVO(tokenUserInfoDto);
        }finally {
            redisComponent.cleanCheckCode(checkCodeKey);
        }
    }
    @RequestMapping("/autoLogin")
    public ResponseVO autoLogin(HttpServletResponse httpServletResponse) {
        TokenUserInfoDto tokenUserInfoDto = getTokenInfoDto();
        if(tokenUserInfoDto == null){
            return getSuccessResponseVO("null");
        }
        if(tokenUserInfoDto.getExpireAt() - System.currentTimeMillis() < Constants.REDIS_KEY_EXPIRES_ONE_DAY){
            redisComponent.saveTokenInfo(tokenUserInfoDto);
            saveToken2Cookies(httpServletResponse,tokenUserInfoDto.getToken());

        }
        saveToken2Cookies(httpServletResponse,tokenUserInfoDto.getToken());
            return getSuccessResponseVO(tokenUserInfoDto);
    }
    @RequestMapping("/logout")
    public ResponseVO logout(HttpServletResponse httpServletResponse) {
            cleanCookies(httpServletResponse);
            return getSuccessResponseVO(null);
    }
}