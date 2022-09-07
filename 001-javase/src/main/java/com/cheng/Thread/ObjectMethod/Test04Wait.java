package com.cheng.Thread.ObjectMethod;

import com.cheng.Thread.util.ThreadUtils;

public class Test04Wait {

    private static int i = 0;

    public static void main(String[] args) {

        //线程锁使用类相关的
        new Thread(()->{
            synchronized (Test04Wait.class){
                while (i == 0){
                    try {
                        System.out.println("开始等待..");
                        Test04Wait.class.wait();
                        System.out.println("等待结束..");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        ThreadUtils.sleep(2);

        synchronized (Test04Wait.class){
            System.out.println("开始唤醒了");
            Test04Wait.class.notify();
            System.out.println("唤醒结束，已经唤醒了");
            i=1;
        }
    }
}
