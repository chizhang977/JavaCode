package com.cheng.reflect;

import java.util.ArrayList;

public class objectAnalyzerTest {

    public static void main(String[] args) throws IllegalAccessException {

        ArrayList<Integer> squares = new ArrayList<>();

        for (int i = 0; i <= 5; i++){
            squares.add(i * i);

            System.out.println(new objectAnalyzer().toString(squares));
        }
    }
}
