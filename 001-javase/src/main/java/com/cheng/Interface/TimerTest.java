package com.cheng.Interface;

import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import javax.swing.*;

public class TimerTest {
    public static void main(String[] args) {
        TimePrinter listener = new TimePrinter();

        Timer timer = new Timer(1000,listener);
        timer.start();

        JOptionPane.showMessageDialog(null,"Quit program??");
        System.exit(0);


    }

    @Test
    public void test(){
        JOptionPane.showMessageDialog(null,"确定关闭吗?");


    }
}
class TimePrinter implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("At the tone,the time is "+  Instant.ofEpochMilli(e.getWhen()));
        Toolkit.getDefaultToolkit().beep();//发出声音
    }


}