package com.resjz.datasources.annotation;

import java.lang.annotation.*;

/**
 * 多数据源注解
 * crazydog
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String name() default "";
}
