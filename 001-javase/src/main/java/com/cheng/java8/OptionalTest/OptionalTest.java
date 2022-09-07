package com.cheng.java8.OptionalTest;

import com.cheng.java8.MethodRef.Employee;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Consumer;

public class OptionalTest {

    /**
     * Optional.of(T t) : 创建一个 Optional 实例，t必须非空；
     * Optional.empty() : 创建一个空的 Optional 实例
     * Optional.ofNullable(T t)：t可以为null
     */
    @Test
    public void test1(){
        Employee employee = new Employee();
//        employee = null;
        Optional<Employee> employee1 = Optional.of(employee);
        System.out.println(employee1);

        Optional<Object> empty = Optional.empty();
        System.out.println(empty.hashCode());

        employee= null;
        Optional<Employee> employee2 = Optional.ofNullable(employee);
        System.out.println(employee2);
    }
/**
    判断Optional容器中是否包含对象：
    boolean isPresent() : 判断是否包含对象
    void ifPresent(Consumer<? super T> consumer) ：如果有值，就执行Consumer
    接口的实现代码，并且该值会作为参数传给它。
 */

    @Test
    public void test2(){
        Employee employee = new Employee();
        boolean present = Optional.ofNullable(employee).isPresent();
        System.out.println(present);

        Optional.of(employee).ifPresent(new Consumer<Employee>() {
            @Override
            public void accept(Employee employee) {
                System.out.println("Optional的ifPresent方法"+employee);
            }
        });

        Optional.of(employee).ifPresent(emp -> System.out.println("Optional的ifPresent方法"+employee));


    }

    /**
     *  T get(): 如果调用对象包含值，返回该值，否则抛异常
     *  T orElse(T other) ：如果有值则将其返回，否则返回指定的other对象。
     *  T orElseGet(Supplier<? extends T> other) ：如果有值则将其返回，否则返回由
     * Supplier接口实现提供的对象。
     *  T orElseThrow(Supplier<? extends X> exceptionSupplier) ：如果有值则将其返
     * 回，否则抛出由Supplier接口实现提供的异常。
     */

    @Test
    public void test3(){
        Girl girl = new Girl();
        Girl girl1 = Optional.of(girl).get();
        System.out.println(girl1);

        girl = null;
        Optional<Girl> girl2 = Optional.ofNullable(girl);
        System.out.println(girl2.orElse(new Girl("cen")));

    }
}
