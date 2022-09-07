package com.cheng.Thread.CreateThread;


//2、实现Runnable接口来创建线程
public class ThreadTest02 implements Runnable{
    @Override
    public void run() {
        System.out.println("实现Runnable接口，覆盖run方法实现线程");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadTest02());
        t1.start();

        Thread t2 = new Thread(new ThreadTest02());
        t2.start();

        System.out.println("main方法执行");
    }
}
