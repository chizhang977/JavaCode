package com.cheng.reflect;

import com.cheng.emp.Employee;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionTest1 {
    public static void main(String[] args) throws ClassNotFoundException {

        String name ;
        if (args.length > 0) name = args[0];
        else {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name(java.util.Date):");
            name = in.next();
        }

        Class<?> aClass = Class.forName(name);
        //Class getDeclaringClass();返回一个class对象，表示定义了这个构造器，方法或字段的类
        Class<?> declaringClass = aClass.getDeclaringClass();

        Method[] declaredMethods = aClass.getDeclaredMethods();


        for (Method method:declaredMethods){
            //Modifier
            boolean anAbstract = Modifier.isAbstract(method.getModifiers());
            System.out.println(anAbstract);
        }

    }

    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        Employee employee = new Employee("cheng",5000,1987,12,15);

        Class<? extends Employee> aClass = employee.getClass();
        Field f = aClass.getDeclaredField("name");
        f.setAccessible(true);//调试，持久存储，类似机制提供的
        Object o = f.get(employee);
        System.out.println(o);

        f.set(f,"zhangchengbo");
        System.out.println(o);


    }
}
