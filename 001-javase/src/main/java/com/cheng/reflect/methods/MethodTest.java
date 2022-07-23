package com.cheng.reflect.methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodTest {


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method square = MethodTest.class.getMethod("square", double.class);
        Method sqrt = Math.class.getMethod("sqrt", double.class);
        printTable(1,10,10,sqrt);
        printTable(1,10,10,sqrt);
    }

    public static double square(double x){
        return x * x;
    }

    public static void printTable(double from, double to, int n, Method f) throws InvocationTargetException, IllegalAccessException {
        System.out.println(f);

        double dx = (to - from) / (n -1);

        for (double x = from; x <= to; x+=dx){
            double y = (Double)f.invoke(null,x);
            System.out.printf("%10.4f | %10.4f%n",x,y);
        }
    }
}
