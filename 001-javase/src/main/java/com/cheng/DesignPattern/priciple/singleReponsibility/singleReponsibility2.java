package com.cheng.DesignPattern.priciple.singleReponsibility;

public class singleReponsibility2 {
    public static void main(String[] args) {
        RoadVehicle roadVehicle = new RoadVehicle();
        roadVehicle.run("摩托车");
        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");
        WaterVehicle waterVehicle = new WaterVehicle();
        waterVehicle.run("邮轮");

    }
}

//交通工具类（路上的）
//1、遵守单一职责原则
//2、但是这样改动很大，
//3、改进:直接修改Vehicle类
class RoadVehicle{
    public void run(String vehicle){
        System.out.println(vehicle+"在公路上跑...");
    }
}
class AirVehicle{
    public void run(String vehicle){
        System.out.println(vehicle+"在天空上跑...");
    }
}

class WaterVehicle{
    public void run(String vehicle){
        System.out.println(vehicle+"在水上跑...");
    }
}


