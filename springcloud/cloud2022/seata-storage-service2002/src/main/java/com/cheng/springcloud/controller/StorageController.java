package com.cheng.springcloud.controller;

import com.cheng.springcloud.domain.CommonResult;
import com.cheng.springcloud.service.StorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StorageController {

    @Resource
    private StorageService storageService;


    @RequestMapping("/storage/decrease")
    public CommonResult decrease(@RequestParam("productId") Long productId,@RequestParam("count") Integer count){

        storageService.decrease(productId,count);
        return new CommonResult(200,"减库存成功");

    }
}
