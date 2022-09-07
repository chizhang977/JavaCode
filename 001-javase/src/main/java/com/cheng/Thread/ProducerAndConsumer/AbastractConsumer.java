package com.cheng.Thread.ProducerAndConsumer;

/**
 * 消费者
 * 模板类，定义好之后具体的业务由子类去实现
 */
public  abstract class AbastractConsumer implements Runnable{
    @Override
    public void run() {
        while (true){
            consumer();
        }
    }

    protected abstract void consumer() ;
}
