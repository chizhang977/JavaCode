package com.cheng.java8.Lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda表达式的练习
 */
public class LambdaTest02 {

   @Test
    public void  test1(){
       //无参，无返回值
       Runnable r1 = () -> System.out.println("Hello Java");
       r1.run();
   }

    @Test
    public void  test2(){
        //需要一个参数，没有返回值，消费型
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("大家好");

        Consumer<String> consumer1 = (String s) -> {System.out.println(s);};
        consumer1.accept("学习java8的新特性");

    }


    @Test
    public void test3(){
        //类型推断
        Consumer<String> consumer1 = (s) -> {System.out.println(s);};
        consumer1.accept("学习java8的新特性");
    }

    @Test
    public void  test4(){
       //小括号可以省略，参数如果是一个才可以
        Consumer<String> consumer1 = s -> {System.out.println(s);};
        consumer1.accept("学习java8的新特性");
    }

    @Test
    public void  test5(){
        Comparator<Integer> comparator = (o1,o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        int compare = comparator.compare(32, 12);
        System.out.println(compare);
    }


    @Test
    public void  test6(){
       //大括号可以省略，return可以省略
        Comparator<Integer> comparator = (o1,o2) ->  o1.compareTo(o2);
        int compare = comparator.compare(32, 12);
        System.out.println(compare);
    }

    @Test
    public void test7(){
        /**
         * 自定义函数接口
         *  使用@FunctionalInterface注解用来检查是否是一个函数式接口
         *  和@Override注解有相似的地方，只是检查，不写也可以生效
         */
       MyInterface myInterface = () -> System.out.println("自定义函数式接口");
       myInterface.doSome();
    }


}
