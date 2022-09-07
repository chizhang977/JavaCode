package com.cheng.Thread.ThreadPool;

import java.util.concurrent.*;

/**
 * 1、继承Thread类来创建线程
 * 2、通过实现Runnable接口来创建
 * 3、通过实现Callable接口来创建
 * 4、通过Executor框架来实现
 * 5、通过Executors工具类来实现
 */
public class Test01 {
    public static void main(String[] args) {
        Thread thread = new Thread(){//工作机制
            @Override
            public void run() {//工作的任务
                System.out.println("线程1执行");
            }
        };
        thread.setName("t1");
        thread.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2执行");
            }
        }).start();
        //实现callable接口来创建线程
        Thread thread3 = new Thread(new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "线程3执行";
            }
        }));
        thread3.start();

        //通过Executor框架来创建线程
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                1,//核心线程数
                2,//最大的线程数
                15,//保持存活的时间
                TimeUnit.SECONDS,//以秒为单位
                new ArrayBlockingQueue<>(5),//阻塞队列的大小为5
                new ThreadPoolExecutor.CallerRunsPolicy()//拒绝策略
        );
        poolExecutor.execute(() -> {
            System.out.println("工作任务四");
        });
        //用完线程池要将其关闭
        poolExecutor.shutdown();

        //可以使用工具类来创建线程池
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            System.out.println("工作任务5");
        });
        service.shutdown();


    }
}
