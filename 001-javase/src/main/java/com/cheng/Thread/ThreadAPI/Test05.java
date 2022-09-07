package com.cheng.Thread.ThreadAPI;

public class Test05 extends Thread {
    public Test05(){
        System.out.println("Test05...."+Thread.currentThread().getName());//main
    }

    @Override
    public void run() {
        System.out.println("线程执行..."+Thread.currentThread().getName());//t0
    }

    public static void main(String[] args) {
        System.out.println("main执行..."+Thread.currentThread().getName());//t1
        Test05 t1 = new Test05();
        t1.start();
    }


}
