package org.cheng.mianshi;

import org.junit.Test;

public class NiuKeTest {

    @Test
    public void test1(){
        Integer i01 = 59;

        int i02 = 59;

        Integer i03 = Integer.valueOf(59);

        Integer i04 = new Integer(59);

        System.out.println(i01 == i02);
        System.out.println(i01 == i03);

        System.out.println(i03 == i04);//false

        System.out.println(i02 == i04);
    }

    @Test
    public  void  test2(){
        String str = "";
        System.out.print(str.split(",").length);
    }
}
