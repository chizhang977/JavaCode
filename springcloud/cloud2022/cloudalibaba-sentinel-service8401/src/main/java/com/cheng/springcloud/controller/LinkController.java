package com.cheng.springcloud.controller;


import com.cheng.springcloud.service.LinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LinkController {


    @Resource
    private LinkService service;


    @GetMapping("/t1")
    public String getUser1(){
        String user = service.getUser();
        return user;
    }
    @GetMapping("/t2")
    public String getUser2(){
        String user = service.getUser();
        return user;
    }

}
