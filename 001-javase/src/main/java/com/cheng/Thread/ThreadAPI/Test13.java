package com.cheng.Thread.ThreadAPI;
//获取线程的优先级
public class Test13 {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
        },"t1    ");
        thread.start();
        thread.setPriority(10);

        Thread thread2 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
        },"t2   ");
        thread.setPriority(3);
        thread2.start();

        System.out.println(thread.getPriority());//5默认为5
    }
}
