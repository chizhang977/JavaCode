package com.cheng.Thread.ThreadState;

import com.cheng.Thread.util.ThreadUtils;

public class Test03 {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                Thread.yield();
                System.out.println(Thread.currentThread().getName() + ": " + Thread.currentThread().getState());
            }
        }, "t线程");
        t.start();
        ThreadUtils.sleep(1);
        System.out.println(t.getName() + ": " + t.getState());
    }
}
