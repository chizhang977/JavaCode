package com.cheng.springcloud.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cheng.springcloud.service.LinkService;
import org.springframework.stereotype.Service;


@Service
public class ServiceImpl implements LinkService {
    @Override
    @SentinelResource(value = "getUser",blockHandler = "blockHandlerGetUser")
    public String getUser() {
        return "cheng";
    }
    public String blockHandlerGetUser(BlockException e) {
        return "流控cheng";
    }
}
