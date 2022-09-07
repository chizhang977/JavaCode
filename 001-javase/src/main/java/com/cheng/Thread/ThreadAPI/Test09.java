package com.cheng.Thread.ThreadAPI;


//join
public class Test09 {
    private static int sum = 0;

    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName()+"线程执行...start...");

        Thread t = new Thread( () -> {
            for (int i = 0; i < 20; i++) {
                sum += i;
            }
        });
        t.start();

        t.join();

        System.out.println(Thread.currentThread().getName()+"线程执行...end..., sum = " + sum);
    }
}
