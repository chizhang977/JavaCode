package com.cheng.Object.ObjectMethod;

import java.time.LocalDate;
import java.util.Objects;

public class Emp {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Emp(String name, double salary, int year,int month,int day) {
        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.of(year,month,day);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPrecent){
        double raise = salary * byPrecent /100;
        salary += raise;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Emp emp = (Emp) obj;
        return Objects.equals(this.name,emp.name)
                && salary == emp.salary
                && Objects.equals(this.hireDay,emp.hireDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,salary,hireDay);
    }

    @Override
    public String toString() {
        return getClass().getName()+"[name="+name+",salary="+salary+",hireDay="+hireDay+"]";
    }

    public Emp() {
    }
}
