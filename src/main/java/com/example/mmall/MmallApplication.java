package com.example.mmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages= {"com.example.mmall.mapper"})
public class MmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MmallApplication.class, args);
    }

}
