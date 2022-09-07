package com.cheng.Thread.ThreadPool;

import lombok.SneakyThrows;

import java.util.concurrent.*;

public class Test05 {
    public static void main(String[] args) {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                1,
                1,
                15,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                //new MyRejectedExecutionHandler()
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        for (int i = 0; i < 5000; i++) {

            poolExecutor.execute(new MyRunnable(i));
        }

        poolExecutor.shutdown();
    }

    //自定义策略
    static class MyRejectedExecutionHandler implements RejectedExecutionHandler{
//        如果超时队列满了之后，就超时等待一下
        @SneakyThrows
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            executor.getQueue().offer(r,60,TimeUnit.SECONDS);
        }
    }
    //自定义线程工厂
    static class MyRunnable implements Runnable{

        private int i ;

        public MyRunnable(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"::"+i);
        }
    }
}
