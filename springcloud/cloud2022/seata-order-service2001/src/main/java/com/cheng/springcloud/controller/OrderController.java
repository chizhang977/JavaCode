package com.cheng.springcloud.controller;


import com.cheng.springcloud.domain.CommonResult;
import com.cheng.springcloud.domain.Order;
import com.cheng.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    //创建订单
    @GetMapping("/order/create")
    public CommonResult createOrder(Order order){
        orderService.createOrder(order);
        return new CommonResult(200,"订单创建成功");
    }
}
