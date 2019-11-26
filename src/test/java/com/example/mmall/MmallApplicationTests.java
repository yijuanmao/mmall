package com.example.mmall;

import com.example.mmall.factory.YamlPropertySourceFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages= {"com.example.mmall.mapper"})
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:mq-config.yml")
public class MmallApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Environment env;

    @Test
    public void contextLoads() {

        try {

            rabbitTemplate.setExchange(env.getProperty("mmall.exchange.name"));
//            rabbitTemplate.setRoutingKey(env.getProperty("mmall.routing.key.name"));
            rabbitTemplate.convertAndSend(MessageBuilder.withBody("mmall发送".getBytes("utf-8")).build());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        RedisClientUtil.set("hha","hehehe");
      /*  String s = RedisClientUtil.get("hha");
        System.out.println("===>>>> "+s);
        System.out.println("=========》》》》》》》》》》》》 "+ RedisClientUtil.get("aa"));*/

      /*  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf2.format(new Date()+" 00:00:00"));*/

/*        String startTime = "2018-11-10 00:00:00";
        Date startDate = DateUtil.parse(startTime, DateUtil.TIME_PATTERN_DEFAULT);
        startTime = DateUtil.format(new Date(startDate.getTime() - 1000), DateUtil.TIME_PATTERN_DEFAULT);
        System.out.println("startTime："+startTime);*/

//        String ss = "15697360503";


    }

}
