package com.jaychou.entity.enums;

/**
 * ClassName: UserSexEnum
 * Package: com.jaychou.entity.enums
 * Description:
 *
 * @Author: 红模仿
 * @Create: 2025/5/8 - 15:03
 * @Version: v1.0
 */
public enum UserSexEnum {
    MALE(0, "男"),
    FEMALE(1, "女"),
    SECRECY(2, "未知");
    private Integer type;
    private String desc;

    UserSexEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static UserSexEnum getByType(Integer type) {
        for (UserSexEnum userSexEnum : UserSexEnum.values()) {
            if (userSexEnum.getType().equals(type)) {
                return userSexEnum;
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

}