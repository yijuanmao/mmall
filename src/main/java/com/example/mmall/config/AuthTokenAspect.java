package com.example.mmall.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 用于处理token验证AOP
 * @author 真、二
 * @date 11:00 2019/4/15
 */

@Slf4j
@Order(1)
@Aspect
@Component
public class AuthTokenAspect {

	/**
	 * 设置controller切入点
	 */
	@Pointcut("execution(* com.example.mmall.controller.*(..))")
	public void authTokenRequest() { }


}
