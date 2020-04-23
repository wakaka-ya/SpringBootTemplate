package com.wakaka.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Description 批量校验字符串是否为空
 * USER: zfy
 * Date: 2020/2/29 17:31
 */
public class CheckStrUtil {
    public static Boolean checkStringIsNull(String... value) {
        int count = 0;
        for(int i = 0; i < value.length; i++) {
            if(StringUtils.isBlank(value[i])) {
                return false;
            }
            count++;
        }
        if(count == value.length) {
            return true;
        }
        return false;
    }
}
