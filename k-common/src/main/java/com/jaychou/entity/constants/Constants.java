package com.jaychou.entity.constants;

/**
 * ClassName: Constants
 * Package: com.jaychou.entity.constants
 * Description:
 *
 * @Author: 红模仿
 * @Create: 2025/5/8 - 13:39
 * @Version: v1.0
 */
public class Constants {
    public static final Integer ONE= 1;
    public static final Integer ZERO= 0;
    public static final Integer LENGTH_10 = 10;
    public static final String REGEX_PASSWORD = "^(?=.*\\d)(?=.*[a-zA-Z])[\\da-zA-Z~!@#$%^&*_]{8,}$";
    public static final Integer REDIS_KEY_EXPIRES_ONE_MIN= 60000;
    public static final Integer REDIS_KEY_EXPIRES_ONE_DAY= 86400000;
    public static final Integer TIME_SECONDS_DAY= REDIS_KEY_EXPIRES_ONE_DAY / 1000;
    public static final Integer DAY_NUMBER= 7;
    public static final String REDIS_KEY_PREFIX = "konghub:";
    public static final String REDIS_KEY_CHECK_CODE= REDIS_KEY_PREFIX+"checkCode:";
    public static final String REDIS_KEY_TOKEN_WEB = REDIS_KEY_CHECK_CODE+"token:web:";
    public static final String TOKEN_WEB = "token";
}
