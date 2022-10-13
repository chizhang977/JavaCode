package com.cheng.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cheng.springcloud.entities.CommonResult;

public class CustomerBlockerHandler {
    public static CommonResult handleException(BlockException exception){
        return new CommonResult(2020,"自定义的限流处理信息......CustomerBlockHandler");
    }
}
