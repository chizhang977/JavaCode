package com.cheng.java8.Lambda;

import org.junit.Test;

import java.util.Comparator;

public class LambdaTest01 {

//    无参数，无返回值
    @Test
    public void  test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("你好");
            }
        };

        r1.run();

        Runnable r2 = () -> System.out.println("你好");//箭头操作符
        r1.run();
    }

    @Test
    public void  test2(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        System.out.println(comparator.compare(12, 21));

        //Lambda
        Comparator<Integer> comparator1 = (o1,o2) -> Integer.compare(o1,o2);
        System.out.println(comparator1.compare(32, 21));
        //方法引用
        Comparator<Integer> comparator2 = Integer :: compare;
        System.out.println(comparator2.compare(32, 21));

    }
}
