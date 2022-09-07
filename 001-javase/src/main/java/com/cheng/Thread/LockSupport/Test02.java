package com.cheng.Thread.LockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 工具类中有很多可以阻塞进程的方法
 * park(),parkNanos(),parkUntil(),
 */
public class Test02 {
        public static void main(String[] args) {
                System.out.println(TimeUnit.SECONDS.toNanos(1));//一秒等于1亿纳秒

                Thread thread = new Thread(() -> {
                        System.out.println("当前线程为:" + Thread.currentThread().getName() + "线程已经进来了");
                        //  LockSupport.park();//阻塞当前线程
                        //LockSupport.parkNanos(1000000000);//一秒，单位为纳秒
                        //  LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(100));
                        LockSupport.parkUntil(System.currentTimeMillis()+3000L);
                        System.out.println("此线程已经被唤醒了...");
                }, "t1");
                thread.start();

                new Thread(()->{
                        System.out.println("当前线程执行唤醒操作..."+Thread.currentThread().getName());
                        LockSupport.unpark(thread);
                },"t2").start();
        }
}
