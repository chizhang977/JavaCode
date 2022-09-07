package com.cheng.java8.Stream;

import com.cheng.java8.MethodRef.Employee;
import com.cheng.java8.MethodRef.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPITest3 {

    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();
        //allMatch(Predicate p)——检查是否匹配所有元素。
        //练习：是否所有的员工的年龄都大于18

        boolean allMatch = employees.stream().allMatch(emp -> emp.getAge() > 18);
        System.out.println(allMatch);

        //        anyMatch(Predicate p)——检查是否至少匹配一个元素。
        //         练习：是否存在员工的工资大于 10000
        boolean anyMatch = employees.stream().anyMatch(emp -> emp.getSalary() > 10000);
        System.out.println(anyMatch);
//        noneMatch(Predicate p)——检查是否没有匹配的元素。
//          练习：是否存在员工姓“雷”
        boolean noneMatch = employees.stream().noneMatch(emp -> emp.getName().startsWith("雷"));
        System.out.println(noneMatch);

//        findFirst——返回第一个元素
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println(first);
        Optional<Employee> any = employees.stream().findAny();
        System.out.println(any);
    }

    /**
     * count() 返回流中元素总数
     * max(Comparator c) 返回流中最大值
     * min(Comparator c) 返回流中最小值
     * forEach(Consumer c)
     * 内部迭代(使用 Collection 接口需要用户去做迭代，
     * 称为外部迭代。相反，Stream API 使用内部迭
     * 代——它帮你把迭代做了)
     */
    @Test
    public void test2(){
        List<Employee> employees = EmployeeData.getEmployees();
        long count = employees.stream().count();
        System.out.println(count);

        Stream<Double> doubleStream = employees.stream().map(Employee::getSalary);
        Optional<Double> max = doubleStream.max(Double::compare);
        System.out.println(max);

        Optional<Double> min = employees.stream().map(Employee::getSalary).min(Double::compareTo);
        System.out.println(min);

        Optional<Employee> min1 = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(min1);

        employees.stream().forEach(System.out::println);



    }

    @Test
    public void test3(){
//        reduce(T identity, BinaryOperator)——可以将流中元素反复结合起来，得到一个值。返回 T
//        练习1：计算1-10的自然数的和
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer reduce = list.stream().reduce(0, Integer::sum);
        System.out.println(reduce);

 //       reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。返回 Optional<T>
//        练习2：计算公司所有员工工资的总和
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Double> doubleStream = employees.stream().map(Employee::getSalary);
        Optional<Double> reduce1 = doubleStream.reduce(Double::sum);
        System.out.println(reduce1);

        Optional<Double> reduce2 = employees.stream().map(emp -> emp.getSalary()).reduce((e1, e2) -> e1 + e2);
        System.out.println(reduce2);

    }

    @Test
    public void test4(){
        //        collect(Collector c)——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
//        练习1：查找工资大于6000的员工，结果返回为一个List或Set
        Long begin = System.currentTimeMillis();
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Employee> employeeStream = employees.stream().filter(emp -> emp.getSalary() > 6000);
        List<Employee> collect = employeeStream.collect(Collectors.toList());
        collect.forEach(System.out::println);
        Long end = System.currentTimeMillis();
        System.out.println(end - begin);

        long begin1 = System.currentTimeMillis();

        employees.stream().filter(emp -> emp.getSalary() > 6000)
                .collect(Collectors.toSet())
                .forEach(System.out::println);
        Long end1 = System.currentTimeMillis();
        System.out.println(end1- begin1);
    }

}
