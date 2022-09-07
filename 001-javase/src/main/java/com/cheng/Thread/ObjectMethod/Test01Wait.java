package com.cheng.Thread.ObjectMethod;

import com.cheng.Thread.util.ThreadUtils;
//wait() notify() notifyAll();
public class Test01Wait {

    private static Object MONITOR = new Object();
    private static Test01Wait test01 = new Test01Wait();

    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main方法开始..."+Thread.currentThread().getName());

        Thread thread = new Thread(() -> {
            ThreadUtils.sleep(5);
            System.out.println("开始通知...");

            synchronized (MONITOR){
                MONITOR.notify();
                i = 1;
                System.out.println("结束通知");
                ThreadUtils.sleep(5);
            }
        });
        thread.start();

        //使用wait方法来让main线程等待
        synchronized (MONITOR){
            while (i==0){
                MONITOR.wait();//等待并释放锁
            }
        }

        System.out.println("main方法结束..."+Thread.currentThread().getName());
    }
}
