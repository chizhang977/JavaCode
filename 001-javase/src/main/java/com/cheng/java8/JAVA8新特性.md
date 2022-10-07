# JAVA8新特性

## 1、Lambda表达式

- ##### 了解：jjs.exe 运行js代码

- ```java
  //举例:(o1,o2)->Inter.compare(o1,o2)
  // -> : Lambda操作符
  // 左边: Lambda的形参列表(抽象方法的形参)
  // 右边: Lambda的体(重写的抽象方法体)
  // 本质:作为函数式接口(只声明一个抽象 方法)的实例
  /**
  	左边:
  		参数类型可以省略
  		如果参数列表只有一个参数，其一对()可以省略
  	右边:
  		使用大括号包裹
  		如果只有一条执行语句，可能是return、语句，省略{}和return
  
  */
  ```

- Lambda看作为一个匿名函数

## 2、函数式（Functional）接口

- 只包含一个抽象方法的接口称为函数式接口
- 使用@FunctionalInterface注解检验是否是一个函数式接口，javadoc声明为函数式接口
- 四大核心函数式接口
  - Consumer->void accept();消费型
  - Supplier->T  get()供给型
  - Function->R  apply()//函数型
  - Predicate->boolean test()断定型

## 3、方法引用与构造器引用

- 情景：当要传递给Lambda体的操作，已经有了实现的方法，可以使用方法引用
- 方法引用实质上Lambda表达式，Lambda表达式作为函数式接口的实例，所以方法引用也是函数式接口的实例
- 使用:     类/对象 :: 方法名
  - 对象 ::非静态方法
  - 类 :: 静态方法
  - 类 :: 非静态方法 

- 要求：要求接口中的抽象方法的形参列表和返回值类型与方法引用方法的形参列表和返回值类型相同。
- 构造器引用
  - 和方法引用类似，函数式接口抽象方法形参列表和 构造器的形参列表一致， 为构造器所属的类型

## 4、强大的Stream API（“集合讲的是数据，Stream讲的是计算！）

- Stream 和 Collection 集合的区别：Collection 是一种静态的内存数据 结构，而 Stream 是有关计算的。前者是主要面向内存，存储在内存中， 后者主要是面向 CPU，通过 CPU 实现计算。

- 注意

  - ##### ①Stream 自己不会存储元素。

  - #####  ②Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。

  - #####  ③Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。

- 创建Stream(一个数据源（如：集合、数组），获取一个流)

  -  通过集合

    - ```java
       default Stream<E> stream() // 返回一个顺序流
       default Stream<E> parallelStream() // 返回一个并行流
      ```

  - 通过数组

    - Arrays类的static <T> Stream<T> stream(T[]  array)

  - 通过Stream的of()方法

  - 迭代 public static Stream iterate(final T seed, final UnaryOperator f)

  -  生成 public static Stream generate(Supplier s) 

- 中间操作(一个中间操作链，对数据源的数据进行处理)

  - 筛选与切片

    -  过滤：filter(Predicate p)——接收 Lambda ， 从流中排除某些元素。
    - 截断流： limit(n)——截断流，使其元素不超过给定数量。
    - 跳过元素:skip():   skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
    - 筛选： distinct()    

  - 映射

    -  map(Function f)——接收一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素。

    - flatMap(Function f)——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。

  - 排序

    - sorted() 产生一个新流，其中按自然顺序排序 
    - sorted(Comparator com) 产生一个新流，其中按比较器顺序排序

- 终止操作（一旦执行种植操作，执行中间操作）
  - 匹配与查找
    - allMatch(Predicate p) 检查是否匹配所有元素
    - anyMatch(Predicate p)——检查是否至少匹配一个元素。
    - noneMatch(Predicate p)——检查是否没有匹配的元素。
    -    findFirst——返回第一个元素
    - count() 返回流中元素总数 
    - max(Comparator c) 返回流中最大值
    - min(Comparator c) 返回流中最小值
    -  forEach(Consumer c) 内部迭代(使用 Collection 接口需要用户去做迭代， 称为外部迭代。相反，Stream API 使用内部迭 代——它帮你把迭代做了)
  - 规约
    - reduce(T iden, BinaryOperator b) 可以将流中元素反复结合起来，得到一 个值。返回 
    - T reduce(BinaryOperator b) 可以将流中元素反复结合起来，得到一 个值。返回 Optional
  - 收集
    - collection

## 5、Optional类

- java.util.Optional是要给容器类，可以避免空指针
- API
  - 