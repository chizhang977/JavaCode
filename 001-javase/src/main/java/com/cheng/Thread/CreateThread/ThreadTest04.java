package com.cheng.Thread.CreateThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//4、通过创建线程池来创建
public class ThreadTest04 implements Runnable{
    @Override
    public void run() {
        System.out.println("线程执行.....");
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10 ;i++){
            ThreadTest04 thread = new ThreadTest04();
            executorService.execute(thread);//分支
        }

//        关闭线程池
        executorService.shutdown();

        System.out.println("main方法执行");
    }
}
