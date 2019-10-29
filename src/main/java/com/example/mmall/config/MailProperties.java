package com.example.mmall.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * MailProperties配置类，用于解析mail开头的配置属性
 * @author zhener
 * @date 14:49 2019/10/28
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperties {

	private String host;

	private Integer port;

	private String userName;

	private String password;

	private String subject;
}
