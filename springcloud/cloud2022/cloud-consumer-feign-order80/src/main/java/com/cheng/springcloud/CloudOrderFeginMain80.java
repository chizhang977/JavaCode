package com.cheng.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CloudOrderFeginMain80 {
    public static void main(String[] args) {
        SpringApplication.run(CloudOrderFeginMain80.class,args);
    }
}
