package com.cheng.Object;

import com.cheng.emp.Student;

import java.util.Arrays;
import java.util.Objects;

public class EqualsTest {
    public static void main(String[] args) {
        Object obj = new Student("cheng","30000");
        Object obj1 = new Student("cheng","30000");

        Student employee = (Student) obj;
        Student employee2 = (Student) obj1;
        System.out.println(employee.getDescription());
        System.out.println("==============");
        String  x = "cheng";
        /**
         * java中equals()
         * 1、自反性: 对于任何非空应用x,x.equals(x)应该返回true
         * 2、对称性: 对于任何引用x和y,当且仅当y.equals(x)为true，y.equals(x)返回为true;
         * 3、传递性:
         * 4、一致性:如果x和y的引用对象没有发生变化，反复调用x.equals(y)应该是同样结果
         * 5、x.equals(null)返回false
         */
        //自反性
        System.out.println(employee.equals(employee));//true
        System.out.println(x.equals(x));//true
        //对称性
        System.out.println(employee.equals(employee2));//true
        System.out.println(employee2.equals(employee));//true
        System.out.println(employee.equals(null));//false
        //对于数组类型的字段，可以使用静态的Arrays.equals方法检测相应的数据元素是否相等
        int[] arr = {1,2,3};
        int[] arrcopy =  new int[3];
        arrcopy = Arrays.copyOf(arr,arr.length);
        System.out.println("复制过来的数组是否等于原数组:"+Arrays.equals(arrcopy,arr));
        int[] rra = {3,2,1};
        System.out.println("两个数组是否相等:"+Arrays.equals(arr, rra));

        //java.util.Objects
        boolean equals = Objects.equals(employee, employee2);
        System.out.println(equals);


    }
}
