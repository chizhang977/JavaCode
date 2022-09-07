package com.cheng.Thread.ThreadState;

import com.cheng.Thread.util.ThreadUtils;

public class Test01 {
        public static void main(String[] args) {
                Thread thread = new Thread();
                System.out.println("线程的状态..."+thread.getState());//线程的状态...NEW

                Thread thread1 = new Thread(() -> {
                        System.out.println(Thread.currentThread().getName()+"在执行中");
                });
                thread1.start();
                ThreadUtils.sleep(2);
                System.out.println("线程的状态"+thread1.getState());//线程的状态TERMINATED

        }
}
