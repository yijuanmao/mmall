package com.example.mmall.rabbitmq.consumer;

import com.example.mmall.common.exception.BizException;
import com.example.mmall.pojo.Mail;
import com.example.mmall.rabbitmq.BaseConsumer;
import com.example.mmall.rabbitmq.MessageHelper;
import com.example.mmall.util.MailUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailConsumer implements BaseConsumer {

    @Autowired
    private MailUtil mailUtil;

    public void consume(Message message, Channel channel){
        Mail mail = MessageHelper.msgToObj(message, Mail.class);
        log.info("收到消息: {}", mail.toString());

        boolean success = mailUtil.sendEmail(mail);
        if (!success) {
            throw new BizException("send mail error");
        }
    }
}
