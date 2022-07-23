package com.cheng.emp;

public class StaticTest {

    public static void main(String[] args) {
        Employee[] staff = new Employee[4];
        Manager boss = new Manager("cheng",100000,2000,04,12);

        boss.setBonus(5000);
        staff[0] = new Employee("tom",40000);
        staff[1] = new Employee("Dick",60000);
        staff[2] = new Employee("Harry",60000);
        staff[3] = boss;

        for (Employee e:
             staff) {
            e.setId();
            System.out.println("name=" + e.getName() + ",id=" + e.getId() + ",salary=" + e.getSalary());
        }

        int nextId = Employee.getNextId();
        System.out.println("next available id = " + nextId);
    }
}
