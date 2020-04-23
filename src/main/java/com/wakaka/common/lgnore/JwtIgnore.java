package com.wakaka.common.lgnore;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
/**
 * Description 忽略Token检查
 * USER: zfy
 * Date: 2020/4/1 11:07
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JwtIgnore {
 
}