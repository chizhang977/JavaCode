package com.cheng.Thread.ThreadAPI;

public class Test14Wait {

    private static Object object = new Object();
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main开始"+Thread.currentThread().getName());

//        想让main线程在此处等待

//        object.wait();//IllegalMonitorStateException
        synchronized (object){
            object.wait(3000);
        }

        System.out.println("main结束"+Thread.currentThread().getName());
    }
}
