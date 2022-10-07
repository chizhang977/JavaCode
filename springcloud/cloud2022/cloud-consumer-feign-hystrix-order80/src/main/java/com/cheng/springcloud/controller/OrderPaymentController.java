package com.cheng.springcloud.controller;


import com.cheng.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@DefaultProperties(defaultFallback = "pyment_Gloabl_TimeOutHandler")
public class OrderPaymentController {
    @Resource
    PaymentService paymentService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Long id){
        return paymentService.paymentInfo_ok(id);
    }

   /* @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")})*/
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand
    public String paymentInfo_timeout(@PathVariable("id") Long id){
        return paymentService.paymentInfo_timeout(id);
    }

    public String paymentInfoTimeoutHandler(@PathVariable("id") Long id){
        return "客户端运行出现异常或者出现错误"+Thread.currentThread().getName();
    }

    public String pyment_Gloabl_TimeOutHandler(){
        return "全局处理，对方繁忙或者运行出错";
    }
}
