package com.cheng.springcloud.controller;

import com.cheng.springcloud.entities.CommonResult;
import com.cheng.springcloud.entities.Payment;
import com.cheng.springcloud.lb.LoadBalancer;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;


    //URL
    public static final String PaymentSrv_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;  //80调用8001

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance instance = loadBalancer.instance(instances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
    @GetMapping("/payment/create")
    public CommonResult create(Payment payment){
       return restTemplate.postForObject(PaymentSrv_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/payment/forEntity/create")
    public CommonResult create1(Payment payment){
        return restTemplate.postForEntity(PaymentSrv_URL+"/payment/create",payment,CommonResult.class).getBody();
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPayment(@PathVariable Long id){
        return restTemplate.getForObject(PaymentSrv_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/payment/getEntity/{id}")
    public CommonResult getPayment2(@PathVariable Long id){
        ResponseEntity<CommonResult> forEntity =
                restTemplate.getForEntity(PaymentSrv_URL + "/payment/get/"+id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            log.info(forEntity.getStatusCode()+","+forEntity.getBody()+","+forEntity.getHeaders());
            return forEntity.getBody();
        }else {
            return new CommonResult(444,"访问失败");
        }
    }
}
