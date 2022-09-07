package com.cheng.Thread.ThreadState;
import com.cheng.Thread.util.ThreadUtils;

import java.util.concurrent.locks.LockSupport;

public class Test05 {
        private final static Object MONITOR = new Object();

        public static void main(String[] args) throws InterruptedException {
            Thread t = new Thread(() -> {
                synchronized (MONITOR) {
                    try {
                        MONITOR.wait();
                        while (true) {

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "t线程");
            t.start();

            ThreadUtils.sleep(1);
            System.out.println(t.getName() + ": " + t.getState()); //waiting


            synchronized (MONITOR) {
                //MONITOR.notify();
                MONITOR.notifyAll();
            }
            ThreadUtils.sleep(1);
            System.out.println(t.getName() + ": " + t.getState()); //runnable


            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                    System.out.println("执行: " + i);
                    ThreadUtils.sleep(1);
                }
            }, "t1");
            t1.start();
            ThreadUtils.sleep(1);

            Thread t2 = new Thread(() -> {
                try {
                    t1.join();
                    System.out.println(Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"t2");
            t2.start();
            ThreadUtils.sleep(6);
            System.out.println(t2.getName() + ": " + t2.getState());


            Thread t3 = new Thread(() -> {
                LockSupport.park();
                System.out.println(Thread.currentThread().getState());
            },"t3");
            t3.start();
            ThreadUtils.sleep(1);
            LockSupport.unpark(t3);
            ThreadUtils.sleep(1);
            System.out.println(t3.getState()+":"+t3.getName());


        }
}
