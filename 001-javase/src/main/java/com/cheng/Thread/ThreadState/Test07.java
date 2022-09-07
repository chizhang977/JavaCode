package com.cheng.Thread.ThreadState;

import com.cheng.Thread.util.ThreadUtils;
//BLOCKED
public class Test07 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (Test07.class) {
                ThreadUtils.sleep(300);
            }
        }, "t1线程");
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (Test07.class) {
                ThreadUtils.sleep(300);
            }
        }, "t2线程");
        t2.start();
        ThreadUtils.sleep(1);
        System.out.println(t2.getName() + ": " + t2.getState());

        Test07 test07 = new Test07();
        Thread t3 = new Thread(() -> {
            test07.syn01();
        },"t3");
        t3.start();
        System.out.println(t3.getName()+":"+t3.getState());

        Thread t4 = new Thread(() -> {
            test07.syn02();
        },"t4");
        t4.start();
        ThreadUtils.sleep(1);
        System.out.println(t4.getName()+":"+t4.getState());
    }

    private synchronized void syn02() {
        System.out.println("正在执行syn02方法...");
        ThreadUtils.sleep(30);
    }

    private synchronized void syn01() {
        System.out.println("正在执行syn01方法...");
        ThreadUtils.sleep(30);
    }
}
