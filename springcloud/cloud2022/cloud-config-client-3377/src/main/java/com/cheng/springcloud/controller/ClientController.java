package com.cheng.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ClientController {

    @Value("${config.info}")
    String info;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return "info:"+info;
    }
}
