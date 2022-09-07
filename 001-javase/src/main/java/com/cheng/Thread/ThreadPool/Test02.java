package com.cheng.Thread.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test02 {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                5,//核心线程数
                10,//最大线程数
                15,//保留的存活时间
                TimeUnit.SECONDS,//单位为秒
                new ArrayBlockingQueue<Runnable>(5),//阻塞队列为5
                Executors.defaultThreadFactory(),//默认的工厂
                new ThreadPoolExecutor.CallerRunsPolicy()//默认的拒绝策略
        );
        poolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        //当核心线程处于空闲状态的时候，允许销毁这些核心线程，默认是不允许销毁的。
        poolExecutor.allowCoreThreadTimeOut(true);
        //如果创建线程池后，立即创建好线程，
        poolExecutor.prestartCoreThread();

        for (int i = 0; i < 50; i++) {
            poolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }

        poolExecutor.shutdown();
    }
}
