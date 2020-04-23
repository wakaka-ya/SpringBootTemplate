package com.wakaka.controller_vue;

import com.alibaba.fastjson.JSONObject;
import com.wakaka.common.lgnore.JwtIgnore;
import com.wakaka.jwt.pojo.Audience;
import com.wakaka.util.JsonUtil;
import com.wakaka.util.JwtTokenUtil;
import com.wakaka.util.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/vue")
public class LoginController {

    @Autowired
    private Audience audience;

    @PostMapping("/login")
    @JwtIgnore
    public ResultBody postLogin(HttpServletResponse response, @RequestBody String params) {
        // 这里模拟测试, 默认登录成功，返回用户ID和角色信息
        String userId = UUID.randomUUID().toString();
        String role = "admin";
        JSONObject data = JsonUtil.getJSONObjectFromJson(params);
        String username = data.getString("username");
        String password = data.getString("password");
        // 创建token
        String token = JwtTokenUtil.createJWT(userId, username, role, audience);
        log.info("### 登录成功, token={} ###", token);

        // 将token放在响应头
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        // 将token响应给客户端
        JSONObject result = new JSONObject();
        result.put("token", token);
        return ResultBody.success(result);
    }
}
