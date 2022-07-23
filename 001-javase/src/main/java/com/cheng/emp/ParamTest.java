package com.cheng.emp;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

/**
 * Java中对方法参数能做什么和不能做什么
 * 1、方法不能修改基本数据类型的参数(数值型或布尔型)
 * 2、方法可以改变对象参数的状态
 * 3、方法不能让一个对象参数引用一个新的对象
 */
public class ParamTest {
    public static void main(String[] args) {
        System.out.println("Testing tripleValue:");
        double percent = 10;
        System.out.println("Before:precent="+percent);
        tripleValue(percent);
        System.out.println("After:precent="+percent);

        /**
         * Mehtods can change the stage of Object parameters
         */

        System.out.println("\nTesting tripleSalary");
        Employee harry  = new Employee("Harry",50000);
        System.out.println("Before:salary="+harry.getSalary());
        tripleSalary(harry);
        System.out.println("After:salary="+harry.getSalary());
        /**
         * Methods can't attach new objects to object parameters
         */
        System.out.println("\nTesting swap");
        Employee a = new Employee("Alice",70000);
        Employee b = new Employee("Bob",60000);
        System.out.println("Before:a="+a.getName());
        System.out.println("Before:b="+b.getName());
        swap(a,b);
        System.out.println("After:a"+a.getName());
        System.out.println("After:b"+b.getName());


    }

    public static void tripleValue(double x){
        x = 3 * x;
        System.out.println("End of method x="+x);

    }
    public static void tripleSalary(Employee x){
        x.raiseSalary(200);
        System.out.println("End of method:salary="+x.getSalary());
    }
    public static void swap(Employee x,Employee y){
        Employee temp = x;
        x = y;
        y = temp;
        System.out.println("End of method:x="+x.getName());
        System.out.println("End of method:y="+y.getName());
    }

}

