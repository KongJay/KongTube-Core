package com.jaychou.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: TokenUserInfo
 * Package: com.jaychou.entity.dto
 * Description:
 *
 * @Author: 红模仿
 * @Create: 2025/5/8 - 16:23
 * @Version: v1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenUserInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String nickName;
    private String avatar;
    private String token;
    private Long expireAt;
    private Integer fansCount;
    private Integer currentCoinCount;
    private Integer focusCount;

}
