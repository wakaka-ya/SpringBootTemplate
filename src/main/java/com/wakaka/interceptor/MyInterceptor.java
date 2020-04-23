package com.wakaka.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.wakaka.dao.pojo.SysUser;

public class MyInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		SysUser user = (SysUser) session.getAttribute("user");
		if(user == null) {
			request.setAttribute("msg", "请登录后再做尝试");
			request.getRequestDispatcher("/login.html").forward(request, response);
			return false;
		}
		return true;
	}
}
