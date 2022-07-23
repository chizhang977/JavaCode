package com.cheng.emp;

/**
 * @author:cheng
 * @version:1.0
 * 管理者继承员工类(管理者本就是员工)
 */
public class Manager extends Employee{

    private double bonus;//奖金

    public void setBonus(double bonus){
        this.bonus = bonus;
    }

    /**
     * 重写超类中的getSalary()方法
     *
     * @return 返回薪水(父类)和奖金,不能写成getSalary()+bonus,因为会造成死循环。
     */
    @Override
    public double getSalary() {
        return super.getSalary()+bonus;
    }



    //有参构造
    public Manager(String name,double salary,int year,int month,int day){
        super(name,salary,year,month,day);
        bonus = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Manager manager = (Manager) obj;
        return this.bonus == manager.bonus;
    }

    @Override
    public String toString() {
        return super.toString()+"[bonus="+bonus+"]";
    }
}
