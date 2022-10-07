package com.cheng.DesignPattern.priciple.inversion;

public class DependencyInversion1 {
    public static void main(String[] args) {
       Person person = new Person();
       person.receive(new Email());
    }
}

class Email{
    public String getInfo(){
        return "电子邮件信息: Hello,world";
    }
}
//Person介绍消息
//分析
//1、简单，容易想到
//2、如果我们获取的对象是微信，短信等则此新增类，同时Person也要新增
//相应的接受方法
//3、引入一个抽象的接口，IReceiver表示接受者，这样Person类与接口发生依赖
//Email,wechat,都属于接受的范畴，他们各自实现IReceiver,接口就ok，符合依赖倒置原则
class Person{
    public void receive(Email email){
        System.out.println(email.getInfo());
    }
}
