package com.cheng.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class LambdaTest01 {
    public static void main(String[] args) {
        repeatMessage("吃饭",1000);
    }
    public static void repeatMessage(String text,int delay){
        ActionListener listener = event ->
        {
            System.out.println(text);
            Toolkit.getDefaultToolkit().beep();
        };
        new Timer(delay,listener).start();
    }
}
