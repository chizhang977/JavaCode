package com.cheng.AutoPacking;

import org.junit.Test;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.text.NumberFormat;

import static java.lang.System.out;

public class IntegerTest {
    @Test
    public void test(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);//自动装箱
        list.add(Integer.valueOf(4));

        out.println(list);

        int n = list.get(0);
        out.println(n);//自动拆箱

        int i = list.get(1).intValue();
        out.println(i);

        Integer a = 1000;
        Integer b = 1000;
        out.println(a==b);//false;
        out.println(a.equals(b));//true

        int c = 1000;
        int d = 1000;
        out.println(c==d);//true

        Integer i1 = 1;
        Double d1 = 2.0;
        out.println(true?i1:d1);//1.0;Integer值会自动拆箱，提升为double，在装箱为Double;


    }

    @Test
    public void  test1(){

        //int intValue();将Integer对象值作为一个int返回
        Integer a = 12;
        int i = a.intValue();
        out.println(i);

        //static String toString(int i);
        //返回一个新的String对象，表示指定数值i的十进制表示
        String s = Integer.toString(23);
        out.println(s);

        String s1 = Integer.toString(34, 2);//将34以2进制的字符串返回
        out.println(s1);//100010
        String s2 = Integer.toString(34, 8);
        out.println(s2);//42
        String s3 = Integer.toString(34, 16);
        out.println(s3);//22

        //static int parseInt(String s)
        String name = "255";//数值字符串
        int i1 = Integer.parseInt(name);
        out.println(i1);//255
        int i2 = Integer.parseInt(name, 8);
        out.println(i2);

        //static Integer valueOf(String s)
        //static Integer valueOf(String s,int radix)
        Integer integer = Integer.valueOf(name);
        out.println(integer);


        //可变参数




    }

    /**
     *
     * @param values
     * @return 传入的最大值
     */
    public static double max(double... values){
        double largest = Double.NEGATIVE_INFINITY;
        for (double value : values)
            if (value > largest)
                largest = value;
            return largest;
    }
    @Test
    public void  test2(){
        double max = max(12.3, 23.3, 12.8, 99.0);
        out.println(max);
    }
}
