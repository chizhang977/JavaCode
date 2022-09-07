package com.cheng.emp;

import java.time.LocalDate;

public class Employee extends Person implements Comparable<Employee>{
    private static int nextId = 1;//静态字段

    private String name;
    private double salary;
    private int id;
    private LocalDate hireDay;

    //有参构造
    public Employee(String n,double s,int year,int month,int day){
        name = n;
        salary = s;
        hireDay = LocalDate.of(year,month,day);
    }

    //有参构造
    public Employee(String n,double s){
        name = n;
        salary = s;
        id = 0;
    }
    //无参构造
    public Employee(){}



    @Override
    public String getDescription() {
        return String.format("an employee with a salary of $%.2f",salary);
    }

    //getter | setter
    public String getName(){
        return name;
    }

    public double getSalary(){
        return salary;
    }

    public int getId(){
        return id;
    }

    public void setId(){

        id = nextId;
        nextId++;
    }

    public static int getNextId(){

        return nextId;
    }

    //涨薪方法
    public void raiseSalary(double byPercent){
        double raise = salary * byPercent /100;
        salary += raise;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;//getClass()方法返回一个对象所属的类。
        Employee employee = (Employee) obj;
        return this.name.equals(employee.name)
                && this.salary == employee.salary
                && this.hireDay.equals(employee.hireDay);
    }

    @Override
    public String toString() {
        return this.getClass().getName()+
                "[name="+name+",salary="+salary+",hireDay"+hireDay+"]";
    }

    //main
    public static void main(String[] args){
        Employee e = new Employee("Harry",5000);
        System.out.println(e.getName()+" "+e.getSalary());
    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare(this.salary,o.salary);
    }
}
