package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient 这里不需要这个注解
public class StreamMQMain8803{
    public static void main(String[] args) {
        SpringApplication.run(StreamMQMain8803.class, args);
    }
}
