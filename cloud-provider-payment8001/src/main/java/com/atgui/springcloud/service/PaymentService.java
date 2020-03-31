package com.atgui.springcloud.service;

import com.atgui.springcloud.entites.Payment;
import org.apache.ibatis.annotations.Param;


public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

}
