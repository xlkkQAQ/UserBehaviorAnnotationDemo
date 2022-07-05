package com.xlkk.annotation;


import com.xlkk.vo.HandEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrintLog {
    /**
     * 接口名称
     */
    String value() default "";

    /**
     * 用户行为
     *
     * @return
     */
    HandEnum handle();

    /**
     * 是否将当前日志记录到数据库中
     */
    boolean save() default true;

}
