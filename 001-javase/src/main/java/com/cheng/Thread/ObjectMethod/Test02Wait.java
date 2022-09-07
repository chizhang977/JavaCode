package com.cheng.Thread.ObjectMethod;

import com.cheng.Thread.util.ThreadUtils;
//interupt()方法对wait阻塞的线程不起作用
public class Test02Wait {

    private static Object MONITOR = new Object();
    private static int i = 0;

    public static void main(String[] args) {
        System.out.println("main开始..."+Thread.currentThread().getName());

        Thread thread = new Thread(()->{
            synchronized (MONITOR){
                while (i==0){
                    try {
                        MONITOR.wait();
                        System.out.println("wait..");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        thread.start();
        ThreadUtils.sleep(5);
        thread.interrupt();//不能使用interrupt()方法来中断一个使用wait等待的线程

        System.out.println("main结束..."+Thread.currentThread().getName());
    }
}
