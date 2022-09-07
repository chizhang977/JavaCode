package com.cheng.java8.MethodRef;

import com.cheng.Object.ObjectMethod.Emp;
import org.hamcrest.core.SubstringMatcher;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConstRefTest {

    @Test
    public void test1(){
        //构造器引用
        //Supplier中的T get()
        //Employee的空参构造器：Employee()
        Supplier<Employee> supplier = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        System.out.println(supplier.get());
        System.out.println();

        Supplier<Employee> supplier1 = () -> new Employee();
        System.out.println(supplier1.get());
        System.out.println();
        //构造引用
        Supplier<Employee> supplier2 = Employee::new;
        System.out.println(supplier2.get());
    }

    //Function中的R apply(T t)
    @Test
    public void  test2(){
        Function<Integer,Employee> fun1 = id -> new Employee(id);
        Employee apply = fun1.apply(1001);
        System.out.println(apply);

        Function<Integer,Employee> fun2 = Employee ::new;
        Employee apply1 = fun2.apply(1002);
        System.out.println(apply1);
    }

    //BiFunction中的R apply(T t,U u)
    @Test
    public void test3(){
        BiFunction<Integer,String ,Employee> biFunction = (id,name) -> new Employee(id,name);
        Employee cheng = biFunction.apply(1001, "cheng");
        System.out.println(cheng);

        BiFunction<Integer,String,Employee> biFunction2 = Employee::new;
        Employee lin = biFunction2.apply(1002, "lin");
        System.out.println(lin);
    }


    //数组引用
    //Function中的R apply(T t)

    @Test
    public void test4(){
        Function<Integer,String[]> fun1 = length -> new String[length];
        String[] apply = fun1.apply(5);
        System.out.println(Arrays.asList(apply));

        Function<Integer,String[]> fun2 = String[] ::new;
        String[] apply1 = fun2.apply(3);
        System.out.println(Arrays.asList(apply1));

    }


}
