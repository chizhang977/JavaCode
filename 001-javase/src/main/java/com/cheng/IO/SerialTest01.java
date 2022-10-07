package com.cheng.IO;

import java.io.*;

/**
 * 序列化就是Java中的一个对象流机制，
 *  1、可以将将对象进行流化，进行读写操作，
 *  2、还可以将流化的对象进行网络传输
 *  目的就是为了解决对象流化后进行读写时引发的问题
 *
 *  实现：1、对象类实现Serialiable接口，标注此类可以被序列化
 *       2、对象类中添加序列化版本号
 *                  private  static  final  long  serialVersionUID = 8656128222714547171L;例如
 *       3、通过输出流来操作
 *
 */
public class SerialTest01 {
    public static void main(String[] args) {

        try {
            OutputStream op = new FileOutputStream("D:\\io"+File.separator+"a.txt");
            ObjectOutputStream fos  = new ObjectOutputStream(op);
            fos.writeObject(new Vip("cheng","10"));
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
class Vip implements Serializable {
    private  static  final  long  serialVersionUID = 8656128222714547171L;
    String name;
    String age;
    public Vip(){
    }
    public Vip(String name,String age){
        this.name  = name;
        this.age = age;

    }
}
