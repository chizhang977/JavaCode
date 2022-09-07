package com.cheng.jihekuangjia;

import java.util.*;

/**
 * 如果要使用队列和栈官方推荐书使用ArrayDeque,其次为LinkedList
 *
 * 1、数据结构:
 *      ArrayDeque底层通过数组实现，为了满足可以同时在数组两端插入或删除元素的需求，
 *      该数组还必须是循环的，即循环数组(circular array)，也就是说数组的任何一点都可能被看作起点或者终点
 * 2、   ArrayDeque是非线程安全的(not thread-safe)，当多个线程同时使用的时候，需要程序员手动同步；
 * 3、  该容器不允许放入null元素。
 * 4、  因为是循环数组，所以head不一定总等于0，tail也不一定总是比head大。
 * 5、   扩容：
 *      默认的构造器初始化为16，仅在充满时才调用，即当头部和尾部缠绕成相等时，双端队列的容量翻倍。
 *      复制分两次进行，第一次复制head右边的元素，第二次复制head左边的元素。
 *
 *
 *
 *
 */
public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque arrayDeque = new ArrayDeque();//构造一个初始循环数组，并且初始值为16

        arrayDeque.add("spring");
        arrayDeque.add("springmvc");
        arrayDeque.add("springboot");

        arrayDeque.addFirst("mysql");
        arrayDeque.addFirst("java");

        arrayDeque.addLast("springcloud");

        //删除数组中第一个元素
//        arrayDeque.poll();
//        arrayDeque.pollFirst();
        //返回数组中第一个元素
        Object peek = arrayDeque.peek();
        System.out.println(peek);


//        arrayDeque.add(null);ArrayDeque不能放入null
        Iterator iterator = arrayDeque.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }


    }
}
