package com.cheng.Thread.util;

import java.util.concurrent.TimeUnit;

public class ThreadUtils {

    /*线程休眠*/

    public static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
