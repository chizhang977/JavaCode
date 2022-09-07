package com.cheng.Thread.LockSupport;

import com.cheng.Thread.util.ThreadUtils;

import java.util.concurrent.locks.LockSupport;

public class Test05 {
    public static void main(String[] args) {
        System.out.println("main线程开始....");

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"开始执行....");
            LockSupport.park();
            System.out.println("线程的状态..."+Thread.currentThread().isInterrupted());
            System.out.println(Thread.currentThread().getName()+"执行结束....");
        },"thread");
        thread.start();

        ThreadUtils.sleep(5);
        Thread.interrupted();//线程进行中断，不会出错

        System.out.println("main线程结束....");
    }
}
