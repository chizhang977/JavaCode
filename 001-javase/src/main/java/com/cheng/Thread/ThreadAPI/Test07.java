package com.cheng.Thread.ThreadAPI;

import com.cheng.Thread.util.ThreadUtils;

/**
 * 守护线程thread.setDaemon(true)
 * 守护线程寄生于用户线程
 */
public class Test07 {

    public static void coding(){
        for (;;){
            System.out.println("我在写代码");
            ThreadUtils.sleep(1);
        }
    }
    public static void play(){
        for (int i = 0;i<10;i++){
            System.out.println("我在玩音乐");
            ThreadUtils.sleep(1);
        }
    }
    public static void main(String[] args) {
        System.out.println("main开始执行....");
        Thread thread = new Thread(() -> coding());
        thread.setDaemon(true);
        thread.start();

        Thread thread1 = new Thread(() -> play());
        thread1.start();


        System.out.println("main结束执行...");
    }
}
