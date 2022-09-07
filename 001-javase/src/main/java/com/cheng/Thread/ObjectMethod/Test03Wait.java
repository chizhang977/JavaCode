package com.cheng.Thread.ObjectMethod;

import com.cheng.Thread.util.ThreadUtils;
//notify()/notifyAll()
public class Test03Wait {
    private static Object MONITOR = new Object();
    private static int i = 0;

    public static void main(String[] args) {
        //第一个线程
        Thread thread = new Thread(() -> {
            synchronized (MONITOR){
                while (i==0){
                    try {
                        MONITOR.wait();
                        System.out.println("t1等待结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

        ThreadUtils.sleep(2);

        //第二个线程
        Thread thread1 = new Thread(()->{
            synchronized (MONITOR){
                while (i==0){
                    try {
                        MONITOR.wait();
                        System.out.println("t2线程结束...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();

        ThreadUtils.sleep(1);

        //通知线程该醒醒了
        synchronized (MONITOR){
//            MONITOR.notify();//随机去通知一个线程
            MONITOR.notifyAll();
            i=1;
        }

    }
}
