package com.cheng.springboot.custom;

import com.cheng.springboot.constant.Constant;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

//自定义策略
public class MyRejectedExecutionHandler implements RejectedExecutionHandler {

    private RedisTemplate<Object,Object> redisTemplate;

    public MyRejectedExecutionHandler(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

//        日志打印
        System.err.println("Task " + r.toString() + " rejected from " + executor.toString());

        //2.将任务持久化到redis中
        this.redisTemplate.opsForList().leftPush(Constant.TASK_KEY, r);
    }
}
