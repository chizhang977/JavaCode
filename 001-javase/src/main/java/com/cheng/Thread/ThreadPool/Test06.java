package com.cheng.Thread.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test06 {
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
        poolExecutor.execute(()-> System.out.println(1));
    }
}
