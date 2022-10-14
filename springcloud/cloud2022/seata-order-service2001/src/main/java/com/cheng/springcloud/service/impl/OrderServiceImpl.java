package com.cheng.springcloud.service.impl;

import com.cheng.springcloud.dao.OrderDao;
import com.cheng.springcloud.domain.Order;
import com.cheng.springcloud.service.AccountService;
import com.cheng.springcloud.service.OrderService;
import com.cheng.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class )
    public void createOrder(Order order) {
        log.info("------>开始下单");
        orderDao.createOrder(order);

        log.info("------>order-service中扣减库存开始");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("------>order-service中扣减库存结束");

        log.info("------>order-service中扣减余额开始");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("------>order-service中扣减余额结束");

        log.info("------>下单结束");
    }


}
