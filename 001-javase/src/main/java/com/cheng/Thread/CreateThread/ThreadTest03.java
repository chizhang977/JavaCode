package com.cheng.Thread.CreateThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/*
* 如果需要返回值，可以实现Callable接口
* 不需要返回值的，可以实现Runnable接口
* */
public class ThreadTest03 implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "Thread execute finished";
    }

    public static void main(String[] args) {
//        创建线程
        FutureTask<String > futureTask = new FutureTask<>(new ThreadTest03());
//        启动线程
        new Thread(futureTask).start();//启动

        try {

//            等待任务执行完毕，并返回结果
            String result = futureTask.get();//线程阻塞，得到结果返回知道，在执行下去
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("main方法执行");
    }
}
