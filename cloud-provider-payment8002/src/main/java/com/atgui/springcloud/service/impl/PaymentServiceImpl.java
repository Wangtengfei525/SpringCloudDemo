package com.atgui.springcloud.service.impl;

import com.atgui.springcloud.dao.PaymentDao;
import com.atgui.springcloud.entites.Payment;
import com.atgui.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Resource
    PaymentDao paymentDao;

    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
