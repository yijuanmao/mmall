package com.example.mmall.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhener
 * @date 15:38 2019/10/29
 */
@Configuration
@Slf4j
public class RabbitmqMessage {

	@Autowired
	private Environment env;

	//TODO：发送邮件消息模型
	@Bean
	public Queue mailQueue(){
		return new Queue(env.getProperty("mail.queue.name"),true);
	}

	@Bean
	public DirectExchange mailExchange(){
		return new DirectExchange(env.getProperty("mail.exchange.name"),true,false);
	}

	@Bean
	public Binding mailBinding(){
		return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(env.getProperty("mail.routing.key.name"));
	}
}
