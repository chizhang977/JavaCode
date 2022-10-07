package com.cheng.springcloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ConsumerOrderConotroller {

    @Resource
    private RestTemplate restTemplate;

    public static final String PAYMENTURI = "http://cloud-provider-payment";

    @RequestMapping("/consumer/payment/zk")
    public String paymentInfo(){
        String result = restTemplate.getForObject(PAYMENTURI+"/payment/zk",String.class);
        System.out.println("消费者调用支付服务(zookeeper)--->result:" + result);
        return result;
    }
}
