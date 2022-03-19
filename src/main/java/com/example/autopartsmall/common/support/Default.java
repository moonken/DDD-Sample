package com.example.autopartsmall.common.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.CLASS)

// 用于标记MapStruct中使用的默认构造函数
public @interface Default {
}
