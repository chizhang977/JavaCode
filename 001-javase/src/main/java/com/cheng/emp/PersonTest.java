package com.cheng.emp;

import java.util.ArrayList;
import java.util.List;
import com.cheng.emp.*;
public class PersonTest {

    public static void main(String[] args) {
        Person[] person = new Person[2];

        person[0] = new Employee("cheng",5000);
        person[1] = new Student("cheng","java");

        for (Person p :person){
            System.out.println(p.getName() + "," + p.getDescription());
        }
    }
}
