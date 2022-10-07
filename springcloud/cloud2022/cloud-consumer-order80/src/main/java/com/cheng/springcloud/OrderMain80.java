package com.cheng.springcloud;



import com.cheng.rule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效。以前我们使用的时
 * @LoadBalanced注解，  这个默认的轮询算法，但是现在改成随机的
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(value = "CLOUD-PAYMENT-SERVICE",configuration= MySelfRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
