package com.cheng.springcloud.service;

import org.springframework.stereotype.Component;

@Component //必须加 //必须加 //必须加
public class PaymentFallbackService implements PaymentService
{
    @Override
    public String paymentInfo_ok(Long id) {
        return "-------PaymentFallbackService_ok";
    }

    @Override
    public String paymentInfo_timeout(Long id) {
        return "-------PaymentFallbackService_timeout";

    }


}
