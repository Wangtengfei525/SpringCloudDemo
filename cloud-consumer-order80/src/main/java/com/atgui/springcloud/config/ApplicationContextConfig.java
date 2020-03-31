package com.atgui.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {


    // 使用@LoadBalanced注解赋予RedisTemplate 负载均衡的能力  这样才能通过微服务名称直接调用服务
    //@LoadBalanced  用ribbon做负载均衡一定要加这个注解    如果用自己写的做负载均衡  就可以不用加
    @Bean
    //@LoadBalanced
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
