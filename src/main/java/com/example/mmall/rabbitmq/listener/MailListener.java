package com.example.mmall.rabbitmq.listener;

import com.example.mmall.rabbitmq.BaseConsumer;
import com.example.mmall.rabbitmq.BaseConsumerProxy;
import com.example.mmall.rabbitmq.consumer.MailConsumer;
import com.example.mmall.service.msg.MsgLogService;
import com.example.mmall.util.MailUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Administrator on 2018/8/30.
 */
@Component
@Slf4j
public class MailListener {

    @Autowired
    MailUtil mailUtil;

    @Autowired
    private MailConsumer mailConsumer;

    @Autowired
    private MsgLogService msgLogService;

//    /**
//     * 监听消费邮件发送
//     * @param message
//     */
//    @RabbitListener(queues = "${mail.queue.name}",containerFactory = "singleListenerContainer")
//    public void consumeMailQueue(@Payload byte[] message){
//        try {
//
//            log.info("监听消费邮件发送 监听到消息： {} ",new String(message,"UTF-8"));
//
//            emailUtil.sendEmail("824414828@qq.com","这是测试mq！！");
//        }catch (Exception e){
//            log.error("监听消费邮件发送报错：{}",e);
//        }
//    }

    /**
     * 监听消费邮件发送
     * @author zhener
     * @date 10:47 2019/11/1
    */
    @RabbitListener(queues = "${mail.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeMailQueue(Message message, Channel channel) throws IOException {

        BaseConsumerProxy baseConsumerProxy = new BaseConsumerProxy(mailConsumer,msgLogService);
        BaseConsumer proxy = (BaseConsumer)baseConsumerProxy.getProxy();
        log.info("监听邮件发送，proxy参数为：{}",proxy);
        if(null != proxy){
            proxy.consume(message,channel);
        }
    }
}






























