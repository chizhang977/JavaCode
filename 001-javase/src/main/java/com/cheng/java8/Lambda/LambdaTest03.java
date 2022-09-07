package com.cheng.java8.Lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;


/**
 * Consumer<T> void accept(T t)消费型
 * Supplier T get()供给型
 * Function<T,R> R apply(T t)函数型
 * Predicate<T> test()断定型
 */
public class LambdaTest03 {

   @Test
    public void  test1(){
        happyTime(10000, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("今天发工资，我这月一共为:"+aDouble);
            }
        });
        //Lambda表达式
       happyTime(12000,salary -> System.out.println("发工资为:"+salary));
     }

   public void happyTime(double money,Consumer<Double> cons){
        cons.accept(money);
   }



    @Test
    public void test2(){
        List<String> list = Arrays.asList("北京","南京","天津","东京","西京","普京");

        List<String> filterStrs = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });

        System.out.println(filterStrs);

        //Lambda表达式
        System.out.println(filterString(list, s -> s.contains("京")));
    }
   public List<String> filterString(List<String> list, Predicate<String> pre){
       ArrayList<String> filterList = new ArrayList<>();
       for (String s : list){
           if (pre.test(s)){
               filterList.add(s);
           }
       }
       return filterList;

   }










}
