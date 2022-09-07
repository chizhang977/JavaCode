package com.cheng.Thread.ThreadState;

import com.cheng.Thread.util.ThreadUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程6种状态
 *
 *
 */
public class Test08 {

    Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Test08 test08 = new Test08();

        Thread t1 = new Thread(() -> test08.lock(), "t1线程");
        t1.start();
        ThreadUtils.sleep(1);
        System.out.println(t1.getName() + ": " + t1.getState());

        Thread t2 = new Thread(() -> test08.lock(), "t2线程");
        t2.start();
        ThreadUtils.sleep(1);
        System.out.println(t2.getName() + ": " + t2.getState()); // blocked ???
    }

    public void lock() {
        // 获取锁
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " get the lock");
            // 访问此锁保护的资源
            while (true) {

            }
        } finally {
            // 释放锁
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " release the lock");
        }
    }
}