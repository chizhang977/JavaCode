package com.cheng.DesignPattern.priciple.singleReponsibility;

public class singleReponsibility3 {
    public static void main(String[] args) {
        Vehicle2 vehicle2 = new Vehicle2();
        vehicle2.run("汽车");
        vehicle2.runAir("飞机");
        vehicle2.runWater("邮轮");
    }
}

//交通工具类（路上的）
// 1、没有对类做大的修改，增加方法
//2、虽然没有在类级别上遵守单一职责原则，在方法级别上遵守单一职责原则
class Vehicle2{
    public void run(String vehicle){
        System.out.println(vehicle+"在公路上跑...");
    }

    public void runAir(String vehicle){
        System.out.println(vehicle+"在空中上跑...");
    }

    public void runWater(String vehicle){
        System.out.println(vehicle+"在水中上跑...");
    }
}



