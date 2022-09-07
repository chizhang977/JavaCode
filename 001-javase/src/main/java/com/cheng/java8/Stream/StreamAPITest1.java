package com.cheng.java8.Stream;

import com.cheng.java8.MethodRef.Employee;
import com.cheng.java8.MethodRef.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPITest1 {

// default Stream<E> stream() : 返回一个顺序流
//default Stream<E> parallelStream() : 返回一个并行流

    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();

        Stream<Employee> stream = employees.stream();//顺序流
        stream.forEach(System.out::println);

        Stream<Employee> employeeStream = employees.parallelStream();//并行流
    }

//    static <T> Stream<T> stream(T[] array): 返回一个流

    @Test
    public void test2(){

        int[] arr1 = new int[]{1,2,3,4,5,6,7,8,9};
        IntStream stream = Arrays.stream(arr1);
        stream.forEach(System.out::println);

        Employee e1 = new Employee(1001,"Tom");
        Employee e2 = new Employee(1002,"Jerry");
        Employee[] arr2 = new Employee[]{e1,e2};
        Stream<Employee> stream1 = Arrays.stream(arr2);

    }

//        //创建 Stream方式三：通过Stream的of()

    @Test
    public void test3(){
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7);
    }

    //创建 Stream方式四：创建无限流

    @Test
    public void test4(){

//        public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)

        Stream.iterate(0, x -> x+2).limit(10).forEach(System.out::print);
        System.out.println();
        Stream.iterate(1,x -> x*2).limit(5).forEach(System.out::print);
//        public static<T> Stream<T> generate(Supplier<T> s)
        System.out.println();
        Stream.generate(Math:: random).limit(10).forEach(System.out::println);
    }
}
