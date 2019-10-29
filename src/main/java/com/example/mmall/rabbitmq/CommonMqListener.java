package com.example.mmall.rabbitmq;

import com.example.mmall.util.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/8/30.
 */
@Component
@Slf4j
public class CommonMqListener {

    @Autowired
    EmailUtil emailUtil;

    /**
     * 监听消费邮件发送
     * @param message
     */
    @RabbitListener(queues = "${mail.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeMailQueue(@Payload byte[] message){
        try {

            log.info("监听消费邮件发送 监听到消息： {} ",new String(message,"UTF-8"));

            emailUtil.sendEmail("824414828@qq.com","这是测试mq！！");
        }catch (Exception e){
            log.error("监听消费邮件发送报错：{}",e);
        }
    }
}






























