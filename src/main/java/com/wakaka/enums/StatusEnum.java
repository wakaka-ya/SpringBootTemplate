package com.wakaka.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 用户状态
 * @author 方
 *
 */
public enum StatusEnum {

    /** 有效*/
    ENABLED("ENABLED", "有效"),

    /** 无效*/
    UNABLED("UNABLED", "无效");

    private String code;

    private String message;

    StatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static StatusEnum getBankCheckStateEnumByCode(String code) {
        for (StatusEnum enumObj : StatusEnum.values()) {
            if (StringUtils.equals(enumObj.name(), code)) {
                return enumObj;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String message() {
        return message;
    }

    public Number value() {
        return null;
    }

}
