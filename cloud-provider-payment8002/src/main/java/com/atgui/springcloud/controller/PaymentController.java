package com.atgui.springcloud.controller;


import com.atgui.springcloud.entites.CommonResult;
import com.atgui.springcloud.entites.Payment;
import com.atgui.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    //为了区分具体是哪一个服务提供者
    @Value("${server.port}")
    private String serverPort;


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
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        System.out.println(result.getSerial());
        log.info("***********插入结果+" + result);
        if (result != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, result);
        } else {
            return new CommonResult(444, "没有对应的记录,查询id:" + id, null);
        }

    }


    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }


}
