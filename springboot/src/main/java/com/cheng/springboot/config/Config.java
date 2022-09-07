package com.cheng.springboot.config;

import com.cheng.springboot.custom.MyRejectedExecutionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class Config {

    //线程池1
    @Bean(name = "threadPoolExecutor")
    public ThreadPoolExecutor threadPoolExecutor(RedisTemplate<Object, Object> redisTemplate) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                1,
                1,
                15,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new MyRejectedExecutionHandler(redisTemplate));

        return poolExecutor;
    }
    //线程池2
   /* @Bean(name = "ThreadPoolExecutor2")
    public ThreadPoolExecutor threadPoolExecutor2 () {
        //基于Executor框架实现线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                1,
                15,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2),
                Executors.defaultThreadFactory(),
                //Executors.privilegedThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );
        return threadPoolExecutor;
    }

    //spring提供的线程池
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(16);//配置核心线程数
        poolTaskExecutor.setMaxPoolSize(64);//设置最大的线程数
        poolTaskExecutor.setQueueCapacity(9999);//设置队列的大小
        poolTaskExecutor.setThreadNamePrefix("async-order-");//指定线程的前缀名
        poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//设置线程的拒绝策略
        poolTaskExecutor.initialize();
        return poolTaskExecutor;//初始化
    }*/

    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}

