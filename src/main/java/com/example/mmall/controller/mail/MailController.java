package com.example.mmall.controller.mail;

import com.example.mmall.common.msg.CallBackMsg;
import com.example.mmall.pojo.Mail;
import com.example.mmall.service.mail.MailService;
import com.example.mmall.util.CallBackMsgUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
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
    @Autowired
    MailService mailService;

    @RequestMapping(value = "/send",method = RequestMethod.GET)
    public CallBackMsg sendMail(@Validated Mail mail, Errors errors){

        //TODO 这块丢到业务层
//            rabbitTemplate.setExchange(env.getProperty("mail.exchange.name"));
//            rabbitTemplate.setRoutingKey(env.getProperty("mail.routing.key.name"));
//            rabbitTemplate.convertAndSend(MessageBuilder.withBody("mail发送".getBytes("UTF-8")).build());   // 发送消息

        if(errors.hasErrors()){
            String msg = errors.getFieldError().getDefaultMessage();
            return CallBackMsgUtils.setResult(null,msg,-1);
        }
        boolean o = mailService.send(mail);

        if(o)return CallBackMsgUtils.setResult(null,"邮箱发送成功",0);
        else return CallBackMsgUtils.setResult(null,"邮件发送失败",-1);
    }
}























