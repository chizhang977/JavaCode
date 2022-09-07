package com.cheng.Thread.ThreadAPI;

import com.cheng.Thread.util.ThreadUtils;
//线程:就是开辟一个新的执行路径
public class Test02 {
//    写代码
    public static void coding(){
        for (;;){
            System.out.println("我在写代码...");
            ThreadUtils.sleep(1);
        }
    }

//    读课外书
    public static void readBooks(){
        for (;;){
            System.out.println("我在读课外书...");
            ThreadUtils.sleep(1);
        }
    }


    public static void main(String[] args) {
        System.out.println("main方法开始执行...");
//        coding();
//        readBooks();
        new Thread(()->coding()).start();
//        new Thread(()->readBooks()).start();
        new Thread(Test02 :: readBooks).start();//使用方法的引用
        System.out.println("main方法结束执行...");
    }



}
