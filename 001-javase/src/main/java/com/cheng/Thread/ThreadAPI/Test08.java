package com.cheng.Thread.ThreadAPI;

import com.cheng.Thread.util.ThreadUtils;

public class Test08 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main action...."+Thread.currentThread().getName());
//创建一个线程，run方法中进行循环，每次打印，睡眠一秒
        Thread thread = new Thread(()->{
            for (int i = 0; i < 20; i++) {
                System.out.println("VIP线程...."+i);
                ThreadUtils.sleep(1);
            }
        });
        thread.start();
//main线程里面进行循环
        for (int i = 0; i < 20; i++) {
            System.out.println("main线程执行"+i);
            if (i==10){
                thread.join();//抢断，执行thread,等待执行完毕之后，main才可以执行
            }
        }


        System.out.println("main end...."+Thread.currentThread().getName());
    }
}
