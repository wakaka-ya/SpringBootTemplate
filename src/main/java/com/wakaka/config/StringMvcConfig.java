package com.wakaka.config;

import com.wakaka.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StringMvcConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/home").setViewName("home/home");
		registry.addViewController("/login.html").setViewName("login");
		//WebMvcConfigurer.super.addViewControllers(registry);
		registry.addViewController("/index.html").setViewName("index");
		registry.addViewController("/index").setViewName("index");
	}
	/*@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addInterceptors(registry);
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/login.html","/LoginIn","/loginInfo","/getVerCode","/static/**");
	}*/
	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//拦截路径可自行配置多个 可用 ，分隔开
		registry.addInterceptor(new JwtInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/login.html","/LoginIn","/getVerCode","/static/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
	
}
