package com.cheng.Object.ObjectMethod;

import java.util.Objects;

public class Man extends Emp{

    private double bonus;

    public Man(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        this.bonus =0;
    }


    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object obj) {
         if (!super.equals(obj)) return false;
         Man man = (Man) obj;
         return this.bonus == man.bonus;
    }

    @Override
    public String toString() {
        return super.toString()+"[bonus="+bonus+"]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),bonus);
    }
}
