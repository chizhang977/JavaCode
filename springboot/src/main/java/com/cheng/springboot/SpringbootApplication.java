package com.cheng.springboot;

import com.cheng.springboot.task.MyRunnable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(SpringbootApplication.class, args);

        ThreadPoolExecutor threadPool = app.getBean(ThreadPoolExecutor.class);
        for (int i = 0; i < 15; i++) {
            threadPool.execute(new MyRunnable(i));
        }


    }

}
