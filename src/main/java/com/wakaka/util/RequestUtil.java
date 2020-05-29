package com.wakaka.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;

public class RequestUtil {
    /**
     * 获取请求中的token信息
    * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request){
        String token = Arrays.asList(request.getCookies()).stream().filter(ck -> ck.getName().equals("token")).map(Cookie::getValue).collect(Collectors.toList()).get(0);
        if (StringUtils.isBlank(token)) {
            // 如果cookie中token为空则获取请求头中token
            token = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY).substring(7);
        }
        return token;
    }
}
