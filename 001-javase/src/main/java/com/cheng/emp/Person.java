package com.cheng.emp;
import com.cheng.emp.Employee;
import com.cheng.emp.Student;

public abstract class Person {

    public abstract String getDescription();

    private String name;

    public Person(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Person(){

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() == obj.getClass()) return true;
        Person person = (Person) obj;
        return this.name.equals(person.name);

    }
}

