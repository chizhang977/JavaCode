package com.cheng.springboot.task;

import com.cheng.springboot.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
@EnableScheduling
public class MyTask {
        @Autowired
        private RedisTemplate<Object,Object> redisTemplate;
        @Autowired
        private ThreadPoolExecutor threadPoolExecutor;

    @Scheduled(cron = "0/5 * * * * ?")
    public void task() {
        System.out.println(Thread.currentThread().getName());
        //定时任务扫描redis，然后添加到threadPoolExecutor中执行
        Runnable runnable = (Runnable)redisTemplate.opsForList().rightPop(Constant.TASK_KEY);
        if (null != runnable) {
            threadPoolExecutor.execute(runnable);
            System.out.println("定时任务执行完成.");
        }
    }
}
