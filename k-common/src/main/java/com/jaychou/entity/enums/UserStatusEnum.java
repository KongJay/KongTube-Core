package com.jaychou.entity.enums;

/**
 * ClassName: UserStatusEnum
 * Package: com.jaychou.entity.enums
 * Description:
 *
 * @Author: 红模仿
 * @Create: 2025/5/8 - 14:56
 * @Version: v1.0
 */
public enum UserStatusEnum {
    DISABLE(0, "禁用"),
    ENABLE(1, "启用");
    private Integer status;
    private String desc;
    UserStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
    public static UserStatusEnum getByStatus(Integer status) {
        for (UserStatusEnum userStatusEnum : UserStatusEnum.values()) {
            if (userStatusEnum.getStatus().equals(status)) {
                return userStatusEnum;
            }
        }
        return null;
    }
    public Integer getStatus() {
     return status;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
