package com.example.mmall;

import com.example.mmall.util.DateUtil;
import com.example.mmall.util.RedisClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages= {"com.example.mmall.mapper"})
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        RedisClientUtil.set("hha","hehehe");
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
