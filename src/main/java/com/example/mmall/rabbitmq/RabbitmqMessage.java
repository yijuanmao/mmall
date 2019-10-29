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
		log.info("mail.queue.name Query值为：{}",env.getProperty("mail.queue.name"));
		return new Queue(env.getProperty("mail.queue.name"),true);
	}

	@Bean
	public DirectExchange mailExchange(){
		log.info("mail.exchange.name 通道值为：{}",env.getProperty("mail.exchange.name"));
		return new DirectExchange(env.getProperty("mail.exchange.name"),true,false);
	}

	@Bean
	public Binding mailBinding(){
		log.info("mail.routing.key.name 交换机值为：{}",env.getProperty("mail.routing.key.name"));
		return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(env.getProperty("mail.routing.key.name"));
	}

}
