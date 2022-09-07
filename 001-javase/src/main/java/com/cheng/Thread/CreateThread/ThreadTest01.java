package com.cheng.Thread.CreateThread;

//类继承Thread类创建线程
public class ThreadTest01 extends Thread{
    @Override
    public void run() {
        System.out.println("继承Thread类，重写run方法实现线程");
    }

    public static void main(String[] args) throws InterruptedException {
        //创建线程
        ThreadTest01 t1 = new ThreadTest01();
        t1.start();//分支
        ThreadTest01 t2 = new ThreadTest01();
        t2.start();//分支
//        t1和t2的前后完全时候OS去决定

        Thread.sleep(10L);
        System.out.println("mian方法执行");//主分支
    }
}
