package com.cheng.enum1;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;
import java.lang.Enum;

public class EnumTest {
    public static void main(String... args){
        Scanner in = new Scanner(System.in);

        System.out.println("Enter a size:(SMALL,MEDIUM,LARGE,EXTRA_LARGE)");
        String input = in.next().toUpperCase();
        Size size = Enum.valueOf(Size.class, input);
        System.out.println("size="+size);
        System.out.println("abbreviation="+size.getAbbreviation());

        if (size==Size.EXTRA_LARGE){
            System.out.println("Good job--you paid attention to the _.");
        }


        Size[] values = Size.values();
        System.out.println(Arrays.toString(values));




    }
    @Test
    public void  test(){
        Size small = Size.SMALL;
        Size medium = Size.MEDIUM;
        Size extraLarge = Size.EXTRA_LARGE;
        Size large = Size.LARGE;
        int ordinal = small.ordinal();
        System.out.println(ordinal);
        int ordinal1 = large.ordinal();
        System.out.println(ordinal1);//返回枚举常量在enum声明的位置，位置从0开始计数

        int i = small.compareTo(large);
        System.out.println(i);//-2
    }
}
enum Size{
    SMALL,MEDIUM,LARGE,EXTRA_LARGE;

    private String abbreviation;

    private Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    Size() {

    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
