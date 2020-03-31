package com.atgui.springcloud.controller;


import com.atgui.springcloud.entites.CommonResult;
import com.atgui.springcloud.entites.Payment;
import com.atgui.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    //为了区分具体是哪一个服务提供者
    @Value("${server.port}")
    private String serverPort;


    @Resource
    private DiscoveryClient discoveryClient;

    //数据库新增是post方式

    //这种方式一般不支持浏览器测试，使用postman测试即可
    //浏览器一般不支持post请求，一般支持get请求
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("***********插入结果+" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据成功,serverPort:" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    //读取操作用Get风格
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        System.out.println(result.getSerial());
        log.info("***********插入结果+" + result);
        if (result != null) {
            return new CommonResult(200, "查询成功serverPort:" + serverPort, result);
        } else {
            return new CommonResult(444, "没有对应的记录,查询id:" + id, null);
        }

    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("element:" + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("" + instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getUri());

        }
        return this.discoveryClient;
    }


    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "hi,i'am paymentzipkin server fall back, welcome to";
    }


}
