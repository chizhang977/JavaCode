package com.cheng.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard//开启实时监控功能
public class OrderHystrixDashboard9001 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixDashboard9001.class,args);
    }
}
