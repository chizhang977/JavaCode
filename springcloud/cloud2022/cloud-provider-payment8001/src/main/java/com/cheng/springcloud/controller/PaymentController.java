package com.cheng.springcloud.controller;

import com.cheng.springcloud.entities.CommonResult;
import com.cheng.springcloud.entities.Payment;
import com.cheng.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController

public class PaymentController {

    /**
     * 可以通过这个获取注册中心的服务的信息
     */
    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services){
            log.info("服务为:"+service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances){
            log.info(instance.getServiceId()+"\t "+instance.getHost()+"\t "+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }


    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }

    /**
     * openFegin 的暂停设置
     * @return
     */
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut()
    {
        System.out.println("*****paymentFeignTimeOut from port: "+serverPort);
        //暂停几秒钟线程
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
        return serverPort;
    }

    @GetMapping(value = "/payment/info")
    public String getPaymentInfo(){
        return "PaymentInfo_Payment8001";
    }
}
