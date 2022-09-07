package com.cheng.Thread.ThreadAPI;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 线程getContextClassLoader()
 *启动类加载器（引导类加载器，Bootstrap ClassLoader）
 *          加载扩展类和应用程序类加载器，并指定为他们的父类加载器。
 *          出于安全考虑，Bootstrap启动类加载器只加载包名为java、javax、sun等开头的类
 * 扩展类加载器（Extension ClassLoader）
 *          JDK的安装目录的jre/1ib/ext子目录
 * 应用程序类加载器（系统类加载器，AppClassLoader）
 *       负责加载环境变量classpath或系统属性java.class.path指定路径下的类库
 *       ，Java应用的类都是由它来完成加载
 */
public class Test11 {
    public static void main(String[] args) throws IOException {
        System.out.println(Test11.class.getClassLoader());

        Properties properties = new Properties();
        //InputStream is = String.class.getClassLoader().getResourceAsStream("config.properties");
        InputStream is = Test11.class.getClassLoader().getResourceAsStream("config.properties");
//        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
        properties.load(is);
        System.out.println(properties.getProperty("username"));

//        Thread.currentThread().setContextClassLoader();

    }
}
