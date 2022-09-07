package com.cheng.Thread.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test04 {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                1,
                1,
                15,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                /**
                 * 拒绝策略：
                 *  当核心线程正在执行的时候，阻塞队列已经满了，最大的线程数已经达到，
                 *   此时在向线程池中提交任务执行，就要执行拒绝策略
                 */
                //new ThreadPoolExecutor.AbortPolicy()//RejectedExecutionException,此策略会抛出异常
//                new ThreadPoolExecutor.DiscardPolicy()//直接丢弃任务，不抛出异常，无关紧要的任务时可以使用
//                new ThreadPoolExecutor.DiscardOldestPolicy()//丢弃任务中最为靠前的任务，

                new ThreadPoolExecutor.CallerRunsPolicy()//这种拒绝策略都可以任务执行

        );
        poolExecutor.execute(new MyRunnable(1));
        poolExecutor.execute(new MyRunnable(2));
        poolExecutor.execute(new MyRunnable(3));
        poolExecutor.execute(new MyRunnable(4));

    }

    static class MyRunnable implements Runnable{

        private int i ;

        public MyRunnable(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}
