package com.cheng;


import org.junit.Test;
import static java.lang.System.*;


import java.text.NumberFormat;
import java.util.ArrayList;


public class Test1 {

    @Test
    public void test1(){
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        double x = 0.1;

        out.println(currencyInstance.format(x));//￥0.10
        out.println(percentInstance.format(x));//10%
    }
    @Test
    public void pageckageTest(){
        out.println("静态导入");
    }


}
