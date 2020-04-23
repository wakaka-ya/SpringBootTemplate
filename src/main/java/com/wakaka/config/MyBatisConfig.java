package com.wakaka.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义mybatis的配置
 * 
 * @author 方
 *
 */

@Configuration
public class MyBatisConfig {
	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		// 配置驼峰命名法
		return (configuration) -> configuration.setMapUnderscoreToCamelCase(true);
/*		return new ConfigurationCustomizer() {
			// 配置驼峰命名法
			@Override
			public void customize(org.apache.ibatis.session.Configuration configuration) {
				configuration.setMapUnderscoreToCamelCase(true);
			}
		};*/
	}
}
