package com.wakaka.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
/**
 * 配置扫描包
 * @author 方
 *
 */
@Configuration
@MapperScan(basePackages = "com.wakaka.dao.mapper")
public class MapperManagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(MapperManagerApplication.class, args);
  }
}
