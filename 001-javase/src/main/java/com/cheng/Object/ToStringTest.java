package com.cheng.Object;

import com.cheng.emp.Manager;
import org.junit.Test;

public class ToStringTest {

    @Test
    public void  test(){
        /**
         * toString方法最好通过getClass().getName来获取类名
         */

        Manager manager = new Manager("cheng",20000,1999,06,05);
        System.out.println(manager);
    }
}
