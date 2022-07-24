package com.cheng.Interface;

public class Student implements Person ,Named{
    @Override
    public String getName() {
        return Person.super.getName();
    }
}
