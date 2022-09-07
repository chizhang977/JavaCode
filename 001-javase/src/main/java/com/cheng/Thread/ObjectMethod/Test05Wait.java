package com.cheng.Thread.ObjectMethod;

import com.cheng.Thread.util.ThreadUtils;

public class Test05Wait {

    private static int i = 0;

    public static void main(String[] args) {

        Test05Wait t5 = new Test05Wait();

        new Thread(()->{
            t5.toWait();
        }).start();

        ThreadUtils.sleep(1);

        synchronized (t5){
            System.out.println("开始通知start...");
            t5.notify();
            i=1;
            System.out.println("通知结束end....");
        }

    }

    private synchronized void toWait()  {
        while (i==0){
            try {
                System.out.println("开始等待。。");
                wait();
                System.out.println("等待结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
