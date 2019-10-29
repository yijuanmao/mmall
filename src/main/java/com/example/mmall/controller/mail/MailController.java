package com.example.mmall.controller.mail;

import com.example.mmall.common.msg.CallBackMsg;
import com.example.mmall.util.CallBackMsgUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 邮箱发送
 * @author zhener
 * @date 17:08 2019/10/29
*/

@RestController
@Slf4j
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/send",method = RequestMethod.GET)
    public CallBackMsg sendMail(){
        try {
            rabbitTemplate.setExchange(env.getProperty("mail.exchange.name"));
            rabbitTemplate.setRoutingKey(env.getProperty("mail.routing.key.name"));
            rabbitTemplate.convertAndSend(MessageBuilder.withBody("mail发送".getBytes("UTF-8")).build());

        }catch (Exception e){
            e.printStackTrace();
        }

        log.info("邮件发送完毕----");
        return CallBackMsgUtils.noArgsSucess();
    }
}























