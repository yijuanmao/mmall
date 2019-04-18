package com.example.mmall.config;

import com.example.mmall.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器，在配置类上添加了注解@Configuration，
 * 标明了该类是一个配置类并且会将该类作为一个SpringBean添加到IOC容器内
 * @author 真、二
 * @date 10:33 2019/4/17
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor())
				.addPathPatterns("/**");    // 拦截所有请求，通过判断是否有 @AuthToken 注解 决定是否需要登录
	}

	@Bean
	public AuthenticationInterceptor authenticationInterceptor() {
		return new AuthenticationInterceptor();
	}
}
