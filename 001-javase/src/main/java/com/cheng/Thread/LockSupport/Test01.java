package com.cheng.Thread.LockSupport;

import com.cheng.Thread.util.ThreadUtils;

import java.util.concurrent.locks.LockSupport;

/**
 * LocalSupport 中的part,unpart相当于Object中的wait,notify,notifyAll方法，
 * 都可以进行等待和唤醒线程。。
 */
public class Test01 {
    public static void main(String[] args) {

        System.out.println("main开始...");

        Thread mainThread = Thread.currentThread();

        new Thread(()->{
            ThreadUtils.sleep(3);
            LockSupport.unpark(mainThread);//等待三秒就可以唤醒线程
        }).start();

        LockSupport.park();//阻塞当前的线程

        System.out.println("main结束...");


    }
}
