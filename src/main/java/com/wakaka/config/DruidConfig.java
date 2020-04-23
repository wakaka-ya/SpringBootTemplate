package com.wakaka.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfig {
	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public DataSource druid() {
		return new DruidDataSource();
	}
	/**
	 * 配置监控服务器
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<Servlet> druidServlet(){
		ServletRegistrationBean<Servlet> druidServlet = new ServletRegistrationBean<Servlet>(new StatViewServlet(),"/druid/*");
		Map<String, String> initParams = new HashMap<String, String>();
		
		// 添加IP白名单
		initParams.put("allow","");//默认就是允许所有访问
		// 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
		initParams.put("deny","192.168.15.21");
		// 添加控制台管理用户
		initParams.put("loginUsername","admin");
		initParams.put("loginPassword","123456");
		
		druidServlet.setInitParameters(initParams);
		return druidServlet;
	}
    /**
     * 配置服务过滤器
     *
     * @return 返回过滤器配置对象
     */
    @Bean
    public FilterRegistrationBean<Filter> statFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 忽略过滤格式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
        return filterRegistrationBean;
    }
}
