package com.cheng.Thread.ThreadState;

import com.cheng.Thread.util.ThreadUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

//超时等待
public class Test06 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            ThreadUtils.sleep(5);
        },"t1");
        t1.start();

        //main线程睡眠1秒
        ThreadUtils.sleep(1);
        System.out.println(t1.getName()+":"+t1.getState());//使用sleep()方法可以让线程进入超时等待

        Thread t2 = new Thread(() -> {
            synchronized (Test06.class){
                try {
                    Test06.class.wait(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2");
        t2.start();
        ThreadUtils.sleep(1);
        System.out.println(t2.getName()+":"+t2.getState());//使用wait方法可以让线程进入超时等待

        Thread t3 = new Thread(() -> {
            while (true){

            }
        },"t3");
        t3.start();
        ThreadUtils.sleep(1);
        Thread t4 = new Thread(() -> {
            try {
                t3.join(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t4");
        t4.start();
        ThreadUtils.sleep(1);
        System.out.println(t4.getName()+":"+t4.getState());//使用join()方法来可以超时等待

        Thread t5 = new Thread(() -> {
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(3));
            //LockSupport.parkUntil(System.currentTimeMillis() + 3000);
        }, "t5线程");
        t5.start();
        ThreadUtils.sleep(1);
        System.out.println(t5.getName() + ": " + t5.getState());
    }
}
