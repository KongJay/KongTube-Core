package com.jaychou.component;

import com.jaychou.utils.RedisUtils;
import com.jaychou.entity.constants.Constants;
import com.jaychou.entity.dto.TokenUserInfoDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

import static com.jaychou.entity.constants.Constants.DAY_NUMBER;

/**
 * ClassName: RedisComponent
 * Package: com.jaychou.component
 * Description:
 *
 * @Author: 红模仿
 * @Create: 2025/5/8 - 13:41
 * @Version: v1.0
 */
@Component
public class RedisComponent {
    @Resource
    private RedisUtils redisUtils;

    public String saveCheckCode(String code) {
        String checkCodeKey = UUID.randomUUID().toString();
        redisUtils.setex(Constants.REDIS_KEY_CHECK_CODE + checkCodeKey, code, Constants.REDIS_KEY_EXPIRES_ONE_MIN * 10);
        return checkCodeKey;
    }

    public String getCheckCode(String checkCodeKey) {
        return redisUtils.get(Constants.REDIS_KEY_CHECK_CODE + checkCodeKey).toString();
    }

    public void cleanCheckCode(String checkCodeKey) {
        redisUtils.delete(Constants.REDIS_KEY_CHECK_CODE + checkCodeKey);
    }

    public void saveTokenInfo(TokenUserInfoDto tokenUserInfoDto) {
        String token = UUID.randomUUID().toString();
        tokenUserInfoDto.setExpireAt(System.currentTimeMillis() + Constants.REDIS_KEY_EXPIRES_ONE_DAY * DAY_NUMBER);
        tokenUserInfoDto.setToken(token);
        redisUtils.setex(Constants.REDIS_KEY_TOKEN_WEB + token, tokenUserInfoDto, Constants.REDIS_KEY_EXPIRES_ONE_DAY * DAY_NUMBER);
    }

    public TokenUserInfoDto getTokenInfo(String token) {
        return (TokenUserInfoDto)redisUtils.get(Constants.REDIS_KEY_TOKEN_WEB + token);
    }
    public String saveTokenInfo4Admin(String account) {
            String token = UUID.randomUUID().toString();
            redisUtils.setex(Constants.REDIS_KEY_TOKEN_ADMIN + token, account, Constants.REDIS_KEY_EXPIRES_ONE_DAY);
            return token;
    }
    public String getTokenInfo4Admin(String token) {
        return (String)redisUtils.get(Constants.REDIS_KEY_TOKEN_ADMIN + token);
    }

}