package com.cheng.java8.Stream;

import com.cheng.java8.MethodRef.Employee;
import com.cheng.java8.MethodRef.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPITest2 {

    //筛选与切片
    @Test
    public void test1(){
//        filter(Predicate p)——接收 Lambda ， 从流中排除某些元素。
        //练习：查询员工表中薪资大于7000的员工信息
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().filter(e -> e.getSalary() > 7000).forEach(System.out::println);
//        limit(n)——截断流，使其元素不超过给定数量。
        System.out.println();
        employees.stream().limit(2).forEach(System.out::println);

//       skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
        System.out.println();
        employees.stream().skip(7).forEach(System.out::println);
        employees.stream().skip(9).forEach(System.out::println);//返回空流
//        distinct()——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
        employees.add(new Employee(1010,"刘强东",40,8000));
        employees.add(new Employee(1010,"刘强东",41,8000));
        employees.add(new Employee(1010,"刘强东",40,8000));
        employees.add(new Employee(1010,"刘强东",40,8000));
        employees.add(new Employee(1010,"刘强东",40,8000));
        employees.stream().distinct().forEach(System.out::println);


    }

    //-映 射
    @Test
    public void test2(){
        //        map(Function f)——接收一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        List<String> list = Arrays.asList("aa","bb","cc","dd");
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        //list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);


//        练习1：获取员工姓名长度大于3的员工的姓名。
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().map(emp -> emp.getName())
                 .filter(emp->emp.length() > 3)
                 .forEach(System.out::println);
        employees.stream().map(Employee::getName)
                .filter(emp->emp.length()>3)
                .forEach(System.out::println);

        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest2::fromStringToStream);
        streamStream.forEach(s ->{
            s.forEach(System.out::println);
        });
        System.out.println();


//        flatMap(Function f)——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。

        Stream<Character> characterStream = list.stream().flatMap(StreamAPITest2::fromStringToStream);
        characterStream.forEach(System.out::println);

    }

    //将字符串中的多个字符构成的集合转换为对应的Stream的实例
    public static Stream<Character> fromStringToStream(String str){//aa
        ArrayList<Character> list = new ArrayList<>();
        for(Character c : str.toCharArray()){
            list.add(c);
        }
        return list.stream();

    }
    //排序
    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(12, 43, 65, 34, 87, 0, -98, 7);
        list.stream().sorted().forEach(System.out::println);


        /**
         * 自定义排序:
         *  年龄排序，相等工资排序
         */
        List<Employee> employees1 = EmployeeData.getEmployees();
        employees1.stream().sorted((e1,e2) -> {

            int compare = Integer.compare(e1.getAge(), e2.getAge());
            if (compare != 0){
                return compare;
            }else {
                return -Double.compare(e1.getSalary(),e2.getSalary());
            }
        }).forEach(System.out::println);
    }

}
