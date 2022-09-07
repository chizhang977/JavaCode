package com.cheng.Thread.ThreadState;

import com.cheng.Thread.util.ThreadUtils;

import java.util.concurrent.locks.LockSupport;
//WAITING
public class Test04 {

    private final static Object MONITOR = new Object();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (MONITOR) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t线程");
        t1.start();

        //main线程休眠1秒
        ThreadUtils.sleep(1);
        System.out.println(t1.getName() + ": " + t1.getState());//WAITING状态(执行wait方法)


        //WAITING 等待
        Thread t2 = new Thread(() -> {
            try {
                //等待t1线程完成
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2线程");
        t2.start();
        ThreadUtils.sleep(1);
        System.out.println(t2.getName() + ": " + t2.getState());//执行join方法，可以进入WAITING状态


        Thread thread = new Thread(() -> {
            LockSupport.park();
        });
        thread.start();
        ThreadUtils.sleep(1);
        System.out.println(thread.getName()+":"+thread.getState());//执行park方法可以进入waiting状态


    }
}
