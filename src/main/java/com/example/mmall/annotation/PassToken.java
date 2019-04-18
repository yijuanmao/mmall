package com.example.mmall.annotation;

import java.lang.annotation.*;

/**
 * 通过此注解@PassToken无需认证
 * @author 真、二
 * @date 18:21 2019/4/16
 */

@Documented     //注解表明制作javadoc时，是否将注解信息加入文档。如果注解在声明时使用了@Documented，则在制作javadoc时注解信息会加入javadoc
@Target({ElementType.METHOD,ElementType.TYPE})	//@Target(ElementType.TYPE)——接口、类、枚举、注解   @Target(ElementType.METHOD)——方法
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {

	boolean required() default true;
}