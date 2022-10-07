package com.cheng.DesignPattern.priciple.singleReponsibility;

public class singleReponsibility1 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("汽车");
        vehicle.run("摩托车");
        vehicle.run("飞机");
    }
}

//交通工具类
//方式一：run()违反了单一职责原则
//解决方案：分解成不同类(交通方式)
class Vehicle{
    public void run(String vehicle){
        System.out.println(vehicle+"在公路上跑...");
    }
}
