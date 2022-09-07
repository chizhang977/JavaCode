package com.cheng.Thread.ThreadAPI;

import com.cheng.Thread.util.ThreadUtils;

//interupt()方法:中断线程的，没有中断，只是提示cpu，可以停止了此线程，但是停不停完全看cpu
public class Test12 {
    public static void main(String[] args) throws InterruptedException {

//        Thread thread1 = new Thread(() -> {
//            for (int i = 0; i < 10000; i++) {
//                //通过中断状态可以优雅的关闭一个线程
//                if (Thread.currentThread().isInterrupted()){
//                    System.out.println("Thread1快结束了"+Thread.currentThread().isInterrupted());
//                    return;
//                }
//                System.out.println(Thread.currentThread().getName() + "......" + Thread.currentThread().isInterrupted()+ i);
//            }
//        });
 //       thread1.start();

        Thread thread = new Thread(()->{
//            ThreadUtils.sleep(10);
//          for (int i = 0;i < 10;i++){
            for (;;){
              System.out.println(Thread.currentThread().isInterrupted()+"终端睡眠");
          }
        });
        thread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+i);
            if (i== 50){
                thread.join();
            }
        }

//        thread1.interrupt();//中断一个正在执行的线程，实际上并没有中断，只是给线程打了一个标记(线程中断的状态)


        thread.interrupt();//中断，这里终端一个睡眠中的线程，相当于唤醒一个线程
       
    }
}
