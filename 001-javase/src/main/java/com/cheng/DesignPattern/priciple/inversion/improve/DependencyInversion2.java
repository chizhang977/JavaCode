package com.cheng.DesignPattern.priciple.inversion.improve;

public class DependencyInversion2 {
    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());
        person.receive(new Wechat());
    }
}

interface IReceiver{
    String getInfo();
}
class Email implements IReceiver{
    public String getInfo(){
        return "电子邮件信息: Hello,world";
    }
}
class Wechat implements IReceiver{
    public String getInfo(){
        return "微信信息: Hello,world";
    }
}

class  Person{
    public void receive(IReceiver receiver){
        System.out.println(receiver.getInfo());
    }
}
