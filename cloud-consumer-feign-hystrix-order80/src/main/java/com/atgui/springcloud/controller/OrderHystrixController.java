package com.atgui.springcloud.controller;

import com.atgui.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
//@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")   配置全局服务降级方法  指定了的用指定的方法，没指定的用括号里面指定的方法
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

//    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
//        String result = paymentHystrixService.paymentInfo_TimeOut(id);
//        return result;
//    }


    //@HystrixProperty设置程序自身运行最多几秒钟
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "PaymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
//    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        //int age = 10/0;
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }
    public String PaymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80，对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己，o(╥﹏╥)o";
    }



    //这个方法是为了测试全局异常信息处理机制的
    @GetMapping("/consumer/testGlobal")
    @HystrixCommand
    public  String   testGlobal()
    {
        int a=10/0;
        return "嘻嘻";
    }


    //下面是全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "我是全局的Global异常处理信息，请稍后再试，嘻嘻o(╥﹏╥)o";
    }
}
