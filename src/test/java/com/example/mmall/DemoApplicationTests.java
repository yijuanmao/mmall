package com.example.mmall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages= {"com.example.mmall.mapper"})
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
//        RedisClientUtil.set("hha","hehehe");
     /*   String s = RedisClientUtil.get("hha");
        System.out.println("===>>>> "+s);
        System.out.println("=========》》》》》》》》》》》》 "+ RedisClientUtil.get("aa"));*/

      /*  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf2.format(new Date()+" 00:00:00"));*/

    }

}
