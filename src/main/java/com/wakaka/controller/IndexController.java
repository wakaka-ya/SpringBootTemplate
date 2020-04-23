package com.wakaka.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wakaka.dao.mapper.SysUserloginMapper;
import com.wakaka.dao.pojo.*;
import com.wakaka.util.CheckStrUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wakaka.dao.mapper.SysRoleMapper;
import com.wakaka.dao.pojo.SysRoleExample.Criteria;
import com.wakaka.enums.StatusEnum;
import com.wakaka.service.SysUserService;
import com.wakaka.util.PasswordEncoderUtil;
import com.wakaka.util.VerifyCode;

/**
 * Description 登录控制
 * USER: zfy
 * Date: 2020/1/16 16:47
 */
@Controller
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserloginMapper sysUserloginMapper;

    @GetMapping("/index")
    public String indexHtml() {
        return "index";
    }

    /**
     * 欢迎页
     */
    @GetMapping("/home")
    public String homeHtml() {
        return "home/home";
    }

    /**
     * 登录
     */
    @GetMapping("/")
    public String loginHtml() {
        return "login";
    }

    /**
     * 验证码
     */
    @GetMapping("/getVerCode")
    public void getVerCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            int width = 200;
            int height = 69;
            BufferedImage verifyImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //生成对应宽高的初始图片
            String randomText = VerifyCode.drawRandomText(width, height, verifyImg);
            //单独的一个类方法，出于代码复用考虑，进行了封装。
            //功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符
            request.getSession().setAttribute("valiCode", randomText);
            response.setContentType("image/png");//必须设置响应内容类型为图片，否则前台不识别
            OutputStream os = response.getOutputStream(); //获取文件输出流
            ImageIO.write(verifyImg, "png", os);//输出图片流
            os.flush();
            os.close();//关闭流
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     *
     * @param request
     * @param model
     *
     * @return
     */
    @PostMapping("/LoginIn")
    @ResponseBody
    public String loginIn(HttpServletRequest request, ModelMap model) {
        JSONObject object = new JSONObject();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String verCode = request.getParameter("verCode");
        String valiCode = (String)request.getSession().getAttribute("valiCode");
        //验证码判断
        if(CheckStrUtil.checkStringIsNull(verCode, valiCode) && !StringUtils.equals(verCode.toUpperCase(), valiCode.toUpperCase())) {
            object.put("status", false);
            object.put("msg", "验证码错误");
            return object.toJSONString();
        }
        SysUser sysUser = sysUserService.selectByName(userName);
        if(null == sysUser) {
            object.put("status", false);
            object.put("msg", "账户信息不存在");
            return object.toJSONString();
        }
        //密码SHA+加密
        if(!sysUser.getPassword().equals(PasswordEncoderUtil.encode(password))) {
            object.put("status", false);
            object.put("msg", "账户或密码错误");
            return object.toJSONString();
        }
        //账户状态
        if(!StringUtils.equals(sysUser.getStatus(), StatusEnum.ENABLED.getCode())) {
            object.put("status", false);
            object.put("msg", "账户封禁（请联系管理员）");
            return object.toJSONString();
        }

        request.getSession().setAttribute("user", sysUser);
        object.put("status", true);
        object.put("msg", "登录成功");
        sysUserloginMapper.updateLastLoginTime(sysUser.getUid());
        request.getSession().setAttribute("appName", "Template");
        return object.toJSONString();
    }

    /**
     * 动态获取  菜单列
     *
     * @return
     */
    @PostMapping("/jsonMenu")
    @ResponseBody
    public String jsonMenu(HttpSession session) {
        JSONObject object = new JSONObject();
        SysUser user = (SysUser)session.getAttribute("user");
        SysRoleExample example = new SysRoleExample();
        Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(user.getType());
        List<SysRole> roleList = sysRoleMapper.selectByExampleWithBLOBs(example);
        if(roleList.size() > 0) {
            String menu = roleList.get(0).getRoleMenu();
            object.put("menu", menu);
            object.put("status", true);
        } else {
            object.put("status", false);
        }
        return object.toJSONString();
    }
}
