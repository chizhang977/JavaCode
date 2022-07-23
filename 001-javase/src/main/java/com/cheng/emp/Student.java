package com.cheng.emp;

import java.util.Objects;

public class Student extends Person{

    private String major;

    @Override
    public String getDescription() {
        return "a student majoring in "+major;
    }

    public Student(String name,String major){
        super(name);
        this.major = major;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj== null ) return false;
        if (this.getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return this.getName().equals(student.getName())
                && this.major.equals(student.major);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(),major);
    }
}
