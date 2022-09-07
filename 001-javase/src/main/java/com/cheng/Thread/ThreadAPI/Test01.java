package com.cheng.Thread.ThreadAPI;

import com.cheng.Thread.util.ThreadUtils;
//可以创建多少个线程？？？此电脑为70000多
public class Test01 {
    public static void main(String[] args) {
        int i = 0;
        while (true){
            new Thread(()-> ThreadUtils.sleep(60*10)).start();
            i++;
            System.out.println(i);
        }
    }
}
