package com.cheng.Thread.ThreadAPI;

import com.cheng.Thread.util.ThreadUtils;

/**
 * 线程不能二次启动
 */
public class Test03 {

    public static void main(String[] args) {
        System.out.println("main线程开始执行");
        Thread thread = new Thread(Test03::coding);
        thread.start();

        ThreadUtils.sleep(1);
//        线程不能二次启动
        thread.start();//IllegalThreadStateException

    }

    public static void coding(){
        System.out.println("我在写代码...");
    }
}
