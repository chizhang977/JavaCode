package com.cheng.Thread.ThreadAPI;

public class Test04 {
    public static void coding(){
        System.out.println("我在写代码....."+Thread.currentThread().getName());
    }

    public static void main(String[] args) {

        System.out.println("main执行"+Thread.currentThread().getName());

        new Thread(Test04::coding).start();

        new Thread(Test04::coding).start();
    }
}
