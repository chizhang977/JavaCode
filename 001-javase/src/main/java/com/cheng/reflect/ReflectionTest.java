package com.cheng.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionTest {
    public static void main(String[] args) {

        String name ;
        if (args.length > 0) name = args[0];
        else {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name(java.util.Date):");
             name = in.next();
        }

        //打印类名和父类的名称
        try {
            Class<?> cl = Class.forName(name);
            Class<?> supercl = cl.getSuperclass();//得到父类对象
            String modifiers = Modifier.toString(cl.getModifiers());//获取修饰符列表
            if (modifiers.length() > 0) System.out.print(modifiers+" ");
            System.out.print("class "+cl.getSimpleName());
            if (supercl != null && supercl != Object.class)
                System.out.println(" extends "+supercl.getSimpleName());
            System.out.println("{\n");

            //获取属性
            printFields(cl);
            System.out.println();

            //获取构造方法
            printConstructors(cl);
            System.out.println();
            //获取方法
            printMethods(cl);
            System.out.println();
            System.out.println("}");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printFields(Class<?> cl) {

        //获取类中所有的属性
        Field[] declaredFields = cl.getDeclaredFields();
        for (Field field:declaredFields){
            String s = Modifier.toString(field.getModifiers());
            if (s.length() > 0) System.out.print(s+" ");
            Class<?> type = field.getType();
            System.out.print(type.getSimpleName());
            System.out.print(";");
            System.out.println();
        }
    }

    private static  void printMethods(Class<?> cl) {
        //获取全部的方法
        Method[] methods = cl.getDeclaredMethods();
        for (Method method: methods){
            //方法的修饰符列表
            String modifier = Modifier.toString(method.getModifiers());
            if (modifier.length() > 0) System.out.print(modifier + " ");
            //方法的返回值类型
            Class<?> returnType = method.getReturnType();
            System.out.print(returnType.getSimpleName()+" ");

            //方法的名称
            String name = method.getName();
            System.out.print(name);
            System.out.print("(");
            //方法的参数
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0;i < parameterTypes.length;i++){
                if (i > 0) System.out.print(", ");
                System.out.print(parameterTypes[i].getSimpleName());
            }
            System.out.print(")");
            //方法抛出的异常
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            for (int i = 0; i < exceptionTypes.length; i++) {
                if (exceptionTypes.length > 0) System.out.print("throws");
                System.out.print(exceptionTypes[i].getSimpleName()+", ");
            }
            System.out.println();
        }
    }

    public static void printConstructors(Class cl){
        //获取全部的构造器
        Constructor[] constructors = cl.getDeclaredConstructors();
        //遍历
        for (Constructor constructor : constructors){
            //获取修饰符列表
            String modifiers = Modifier.toString(constructor.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers+" ");
            //获取类名
            String name = cl.getSimpleName();
            System.out.print(name+"(");

            Class[] parameterTypes = constructor.getParameterTypes();//获取参数类型
            //打印参数类型
            for (int i = 0; i < parameterTypes.length; i++){
                if (i > 0) System.out.print(", ");
                System.out.print(parameterTypes[i].getSimpleName());
            }
            System.out.println(")");
        }
    }
}
