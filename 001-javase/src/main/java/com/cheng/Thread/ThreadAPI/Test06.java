package com.cheng.Thread.ThreadAPI;

public class Test06 {
    public static void main(String[] args) {
        System.out.println("main...开始"+Thread.currentThread().getName());

        Thread thread = new Thread(()->{
            System.out.println("线程开始...."+Thread.currentThread().getName());

            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程结束...."+Thread.currentThread().getName());
        });
        thread.start();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main...结束"+Thread.currentThread().getName());
    }
}
