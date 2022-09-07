package com.cheng.java8.MethodRef;


import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodRefTest01 {

    // 情况一：对象 :: 实例方法
    //Consumer中的void accept(T t)
    //PrintStream中的void println(T t)
    @Test
    public void  test1(){
        Consumer<String> con1 = new Consumer<String>() {
            @Override
            public void accept(String o) {
                System.out.println(o);
            }
        };
        con1.accept("对象引用1");

        System.out.println();
        //Lambda
        Consumer<String> con2 = s -> System.out.println(s);
        con2.accept("对象引用2");
        System.out.println();
        //对象引用
        Consumer<String> con3 = System.out :: println;
        con3.accept("对象引用3");


    }

    //Supplier中的T get()
    //Employee中的String getName()
    @Test
    public void test2(){
        Employee employee  = new Employee(1001,"che",12,1000);
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return employee.getName();
            }
        };
        System.out.println(supplier.get());

        Supplier<String> supplier1 = () -> employee.getName();
        System.out.println(supplier1.get());

        Supplier<String > supplier2 = employee :: getName;
        System.out.println(supplier2.get());
    }



    // 情况二：类 :: 静态方法
    //Comparator中的int compare(T t1,T t2)
    //Integer中的int compare(T t1,T t2)

    @Test
    public void test3(){
        Comparator<Integer> com1 = (o1,o2) -> o1.compareTo(o2);
        System.out.println(com1.compare(12, 21));

        Comparator<Integer> com2 = Integer :: compare;
        System.out.println(com2.compare(23, 21));
    }

    //Function中的R apply(T t)
    //Math中的Long round(Double d)
    @Test
    public void test4(){
        Function<Double,Long> function = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return Math.round(aDouble);
            }
        };
        Long apply = function.apply(12.3);
        System.out.println(apply);

        Function<Double,Long> function1 = aDouble -> Math.round(aDouble);
        Long apply1 = function1.apply(12.6);
        System.out.println(apply1);


        Function<Double,Long> function2 = Math :: round;
        Long apply2 = function2.apply(12.5);
        System.out.println(apply2);
    }

    // 情况三：类 :: 实例方法  (有难度)
    // Comparator中的int comapre(T t1,T t2)
    // String中的int t1.compareTo(t2)

    @Test
    public void test5(){

        Comparator<String> com1 = ((o1, o2) -> o1.compareTo(o2));
        System.out.println(com1.compare("abc","abd"));

        Comparator<String > com2 = String :: compareTo;
        System.out.println(com2.compare("abc","abd"));

    }

    //BiPredicate中的boolean test(T t1, T t2);
    //String中的boolean t1.equals(t2)
    @Test
    public void test6(){
        BiPredicate<String ,String > pre = (s1,s2) -> s1.equals(s2);
        boolean test = pre.test("cheng", "cheng");
        System.out.println(test);

        BiPredicate<String ,String > pre1 = String :: equals;
        boolean test1 = pre1.test("lin", "lll");
        System.out.println(test1);
    }

    // Function中的R apply(T t)
    // Employee中的String getName();

    @Test
    public void test7(){
        Employee employee = new Employee(1001, "Jerry", 23, 6000);


        Function<Employee,String> func1 = e -> e.getName();
        System.out.println(func1.apply(employee));

        Function<Employee,String > func2 = Employee :: getName;
        System.out.println(func2.apply(employee));
    }

}
