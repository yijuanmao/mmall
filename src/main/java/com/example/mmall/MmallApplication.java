package com.example.mmall;

import com.example.mmall.factory.YamlPropertySourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableAutoConfiguration //自动加载配置信息
@MapperScan(basePackages= {"com.example.mmall.mapper"})
@ComponentScan("com.example")//使包路径下带有注解的类可以使用@Autowired自动注入
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:mq-config.yml")
public class MmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MmallApplication.class, args);
    }

}
