package com.cheng.Thread.ObjectMethod;

public class Test06Wait {
    private static int i = 0;
    public static void main(String[] args) {

        new Thread(()->{
            toWait();
        }).start();
        
        new Thread(()->{
            toNotify();
        }).start();
    }

    private  synchronized static void toNotify() {
        System.out.println("开始通知");
        Test06Wait.class.notify();
        i=1;
        System.out.println("结束通知");
    }

    private synchronized static void toWait(){
        while (i==0) {
            try {
                System.out.println("开始等待");
                Test06Wait.class.wait();
                System.out.println("结束等待");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}