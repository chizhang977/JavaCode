package com.cheng.Object;

import com.cheng.emp.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;

public class HashCodeTest {

    @Test
    public void test(){
        int hashCode = "hello".hashCode();
        System.out.println(hashCode);//99162322
        int hashCode1 = "Harry".hashCode();
        System.out.println(hashCode1);//69496448
    }

    @Test
    public void test1(){

        String s = "Ok";
        StringBuilder sb = new StringBuilder(s);
        System.out.println(s.hashCode()+","+sb.hashCode());//2556,584634336

        String t ="Ok";
        StringBuilder tb = new StringBuilder(t);
        System.out.println(s.hashCode()+","+tb.hashCode());//2556,1469821799
        /**
         * s和t的散列码是相同的，说明字符串的散列码是由内容导出的
         * 而sb和tb的散列吗是不相同的，说明StringBuilder类没有重写hashCode()方法
         * Object的默认hashcode方法会从对象存储地址的出散列码
         */

    }

    @Test
    public void test3(){
        Student s = new Student("cheng","一年级");
        Student s1 = new Student("cheng","一年级");

        System.out.println(s.equals(s1));
        System.out.println(s.hashCode()==s1.hashCode());
    }
    @Test
    public void test4(){
        String  name = "cheng";
        int hashCode = name.hashCode();
        System.out.println(hashCode);//94627417

        System.out.println(Objects.hash(name));//94627448
        System.out.println(Objects.hashCode(name));//94627417

        int hashCode1 = Integer.hashCode(30);
        System.out.println(hashCode1);//30


        System.out.println(Arrays.hashCode(new int[]{10, 12, 2, 3, 45}));//38223913
        System.out.println(Arrays.hashCode(new Object[]{"name", 23, 34, '2', true, 3.12}));//1235902146
        System.out.println(Arrays.hashCode(new Object[]{new Student("cheng","大学一年级")}));//-1041812005


    }
}
