package com.cheng.springboot.task;

import java.io.Serializable;

public class MyRunnable implements Runnable, Serializable {
    private int i ;

    public MyRunnable(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+":"+i);
    }
}
