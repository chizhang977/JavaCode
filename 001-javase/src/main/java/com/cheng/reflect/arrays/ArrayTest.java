package com.cheng.reflect.arrays;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * java.lang.reflect.Array
 */
public class ArrayTest {
    @Test
    public void  test(){
        int[] array  = new int[]{1,2,3,4,5,6,7,8,9,0};

        System.out.println(Arrays.toString(array));//[1, 2, 3, 4, 5, 6, 7, 8, 9, 0]

        //static Object get(Object array,int index)
        int o = (int) Array.get(array, 0);
        System.out.println(o);//1

        //static xxx getXxx(Object array,int index);
        int anInt = Array.getInt(array, 2);
        System.out.println(anInt);//3

        Array.set(array,0,100);
        System.out.println(Array.get(array,0));//100

        //static int getLength(Object array);
        int[] arraycopy = Arrays.copyOfRange(array, 0, 4);
        int[] arraycopy1 = Arrays.copyOf(array, 5);
        int[] newArray = new int[10];
        System.arraycopy(array,0,newArray,0,9);
        System.out.println(Arrays.toString(arraycopy));//[100, 2, 3, 4]
        System.out.println(Arrays.toString(arraycopy1));//[100, 2, 3, 4, 5]
        System.out.println(Arrays.toString(newArray));//[100, 2, 3, 4, 5, 6, 7, 8, 9, 0]
        System.out.println(Array.getLength(newArray));


        //static Object newInstance(Class componentType,int length);

        String[] s1 = (String[]) Array.newInstance(String.class, 100);
        System.out.println(s1.length);


    }
}
