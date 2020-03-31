package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
@Component
@EnableBinding(Sink.class)

@RestController
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private   String  serverPort;


    private MessageChannel  input;

    @StreamListener(Sink.INPUT)
    public  void  input(Message<String> message)
    {
        System.out.println("消费者一号，接收到的消息是:" + message.getPayload() + "\t port:" + serverPort);
    }

}
