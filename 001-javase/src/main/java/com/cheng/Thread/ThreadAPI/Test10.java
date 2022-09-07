package com.cheng.Thread.ThreadAPI;
//yield:让步，放弃cpu的执行权
public class Test10 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main线程执行...start..." + Thread.currentThread().getName());

        Thread thread = new Thread( () -> {
            long start = System.currentTimeMillis();
            long res = 0;
            for (long i = 1; i <= 1000000; i++) {
                res += i;
                Thread.yield();//让步，放弃CPU执行权
            }
            long end = System.currentTimeMillis();
            System.out.println("运算时间：" + (end - start) + " 结果：" + res);
        });
        thread.start();
        thread.join();

        System.out.println("main线程执行...end..." + Thread.currentThread().getName());
    }
}
