package org.cheng.mianshi;

import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;

public class VariablesAndOperators {

    @Test
    public void  test1(){
        /**
         *
         * 1、变量的初始值为什么?
         *  基本类型:
         *      byte,short,int,long的初始值为0
         *      double,float的初始值为0.0
         *      boolean的初始值为false
         *      char的初始值为空格
         *  引用类型：
         *      初始值为null
         */
        int[] intArray = new int[5];
        System.out.println(Arrays.toString(intArray));//[0, 0, 0, 0, 0]
        long[] longs = new long[5];
        System.out.println(Arrays.toString(longs));//[0, 0, 0, 0, 0]
        boolean[] booleans = new boolean[2];
        System.out.println(Arrays.toString(booleans));//[false, false]
        char[] objects = new char[2];
        System.out.println(Arrays.toString(objects));//[ ,  ]
        String[] strings = new String[2];
        System.out.println(Arrays.toString(strings));//[null, null]

    }
    @Test
    public void test(){
        //2.	用最有效的方法算出2乘以8等于几
        //使用位运算就是最快的，在二分查找的时候就应该使用位运算

        //3.	Java中的基本类型有哪些？String 是最基本的数据类型吗？
        /*
            Java中基本数据类型为
            byte        1       -128~127
            short       2       -32768~32767
            int         4       -2147483648~2147483647
            long        8
            float       4
            double      8
            boolean     1
            char        2
            String不是基本数据类型
         */


        //4.	char型变量中能不能存储一个中文汉字，为什么？
        char a = '中';
        System.out.println(a);
        /*
          Java中的编码使用的Unicode编码，char类型占两个字节，所以存储一个汉字完全没有问题
         */

        //5.	short s1=1; s1=s1+1;有什么错？short s1=1;s1+=1; 有什么错?
        short s1 = 1;
        s1= (short) (s1+1);//因为int和short运算，就会先转成int类型，把int类型赋给s1(short)，应该进行类型转换

        short s2 = 1;
        s2+=1;//+=就是相当于进行强制类型转换
        System.out.println(s2);
    }

    @Test
    public void  test2(){
        //6.	Java中的Integer和int有什么区别？
        /*
            1、Integer是int的包装类，int是java的一种基本数据类型
            2、Integer变量必须实例化后才能使用，而int变量不需要
            3、Integer实际是对象的引用，当new一个Integer时，实际上是生成一个指针指向此对象；而int则是直接存储数据值
            4、Integer的默认值是null，int的默认值是0
         */

        Integer i = new Integer(100);
        Integer j = new Integer(100);
        System.out.println(i == j); //false 因为内存地址不同，所以不相等
        System.out.println(j.equals(i));//true，判断引用类型变量时，需要使用equals，Integer重写了Object中的equals()


        Integer a = new Integer(100);
        int b = 100;
        System.out.println(a == b); //true 自动拆箱机制

        Integer c = new Integer(100);
        Integer d = 100;
        System.out.println(c == d); //false

        Integer e = new Integer(100);//指向堆中
        Integer f = 100;//指向Java常量池中
        System.out.print(e == f); //false，两个的内存地址不同

        Integer g = 100;
        Integer h = 100;
        System.out.println(g==h);//true
        Integer l = 128;
        Integer k = 128;
        System.out.println(l==k);//false

    }

    @Test
    public void  test3(){
        //7.能否在不进行强制转换的情况下将一个 double 值赋值给 long 类型的变量？
        double a = 10.0;
        long b = (long) a;//必须进行强制类型转换

        //8.	java 中 3*0.1 == 0.3 将会返回什么？true 还是 false？
        System.out.println(3*0.1==0.3);//fasle 浮点数会有精度损失

        //9、	&和&&的区别？

        int i = 0;
        i = i++;
        System.out.println(i);

        int x= 1,y =2,z=3;
        System.out.println( y+=z--/++x);//3




    }
}
