package com.cheng.Interface;

import com.cheng.Object.ObjectMethod.Emp;
import com.cheng.emp.Employee;
import org.junit.Test;

import javax.swing.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;


public class LambdaTest {
    public static void main(String[] args) {

        String[] planets = new String[]{"Mercury","Venus","Earth","Mars","Jupiter",
                            "Saturn","Uranus","Neptune"};
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted in dictionary order:");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted by length:");

        Arrays.sort(planets,(first,second)->first.length() - second.length());
        System.out.println(Arrays.toString(planets));

        Timer timer = new Timer(1000,event-> System.out.println("The time is "+new Date()));
        timer.start();

        JOptionPane.showMessageDialog(null,"Quit program?");
        System.exit(0);
    }

    @Test
    public void  test(){

    }
}
