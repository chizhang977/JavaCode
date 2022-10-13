package com.cheng.springcloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cheng.springcloud.entities.CommonResult;
import com.cheng.springcloud.entities.Payment;
import com.cheng.springcloud.service.PaymentFallbackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {

    @Resource
    private PaymentFallbackService paymentFallbackService;

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback",fallback = "fallbackHandler")
    @SentinelResource(value = "fallback",
                      blockHandler = "blockHandler",
                      fallback = "fallbackHandler",
                      exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id")Long id){
       CommonResult<Payment> result =  restTemplate.getForObject(SERVICE_URL+"/paymentSQL/"+id,CommonResult.class,id);
        if (id == 4) {
            throw new IllegalArgumentException("非法参数异常...");
        }else if (result.getData() == null){
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }
        return result;
    }

    public  CommonResult<Payment> fallbackHandler(@PathVariable("id")Long id, Throwable e){
        Payment payment = new Payment(444L,null);
        return new CommonResult<>(444,"运行时出现异常了"+e.getMessage(),payment);
    }
    public CommonResult<Payment> blockHandler(@PathVariable("id") Long id,BlockException e){
        Payment payment = new Payment(444L,null);
        return new CommonResult<>(445,"blockHandler限流了..."+e.getMessage(),payment);
    }


    @GetMapping(value = "/consumer/openfeign/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        if (id == 4){
            throw  new RuntimeException("没有此id");
        }
        return paymentFallbackService.paymentSQL(id);
    }
}
