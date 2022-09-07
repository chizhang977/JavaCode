package com.cheng.Thread.ThreadPool;

import java.util.concurrent.*;
//自定义线程工厂
public class Test03 {
    public static void main(String[] args) {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                5,
                10,
                15,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
//                Executors.defaultThreadFactory(),
//                Executors.privilegedThreadFactory(),
                new MyThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        poolExecutor.prestartCoreThread();//线程池有了之后，里面立马初始化核心线程
        poolExecutor.execute(() -> {
            System.out.println("线程执行");
        });
        poolExecutor.allowCoreThreadTimeOut(true);//如果核心线程处于空闲的状态，并且已经过了超时时间，即可销毁线程
        poolExecutor.shutdown();


    }
    /**
     * 自己实现线程工厂
     */
    static class MyThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"my-create-thread");
        }
    }
}
