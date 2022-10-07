package com.cheng.springcloud.controller;

import com.cheng.springcloud.entities.CommonResult;
import com.cheng.springcloud.entities.Payment;
import com.cheng.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController

public class PaymentController {

    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("****插入结果:"+result);
        if (result > 0){
            return new CommonResult(200,"插入数据成功",result+"服务的端口号"+serverPort);
        }else {
            return  new CommonResult(444,"插入数据失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果:"+payment);
        if (payment != null) {
            return new CommonResult(200,"查询成功",payment+"服务的端口号"+serverPort);
        }else {
            return new CommonResult(444,"查询失败",null);
        }
    }


    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }

}
