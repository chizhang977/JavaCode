package com.cheng.Thread.ThreadState;

import com.cheng.Thread.util.ThreadUtils;

public class Test02 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                //System.out.println(Thread.currentThread().getName() + "运行");
            }
        }, "t1线程");
        t1.start();

        ThreadUtils.sleep(2);
        System.out.println(t1.getName() + ": " + t1.getState());//t1线程: RUNNABLE
    }
}
