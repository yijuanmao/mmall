package com.example.mmall.service.mail.impl;

import com.example.mmall.mapper.msg.MsgLogMapper;
import com.example.mmall.model.msg.MsgLog;
import com.example.mmall.pojo.Mail;
import com.example.mmall.rabbitmq.MessageHelper;
import com.example.mmall.service.mail.MailService;
import com.example.mmall.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @author zhener
 * @date 17:37 2019/10/30
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private Environment env;
	@Autowired
	private MsgLogMapper msgLogMapper;
	@Autowired
	private RabbitTemplate rabbitTemplate;


	/**
	 * 生产消息
	 * @author zhener
	 * @date 14:30 2019/11/1
	*/
	@Override
	public boolean send(Mail mail) {

		try {
			String msgId = RandomUtil.UUID32();
			mail.setMsgId(msgId);

			MsgLog msgLog = new MsgLog(msgId,mail,env.getProperty("mail.exchange.name"),env.getProperty("mail.routing.key.name"));
			msgLogMapper.insert(msgLog);	// 消息入库

			CorrelationData correlationData = new CorrelationData(msgId);
			rabbitTemplate.convertAndSend(env.getProperty("mail.exchange.name"), env.getProperty("mail.routing.key.name"),
					MessageHelper.objToMsg(mail), correlationData);	// 发送消息
			return true;
		} catch (AmqpException e) {
			log.error("邮件业务层发送失败！",e);
		}
		return false;
	}
}
