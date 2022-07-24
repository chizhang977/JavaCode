package com.cheng.Interface;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorTest {
    public static void main(String[] args) {
        LengthComparator comp = new LengthComparator();

        String[] friends = {"cheng","long","peng"};
        Arrays.sort(friends,comp);
        System.out.println(Arrays.toString(friends));
        /**
         * 匿名内部类
         */
        Arrays.sort(friends, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });

    }
}

class LengthComparator implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
}
