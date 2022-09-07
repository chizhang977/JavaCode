package com.cheng.Thread.ProducerAndConsumer;


/**
 * 模板类
 */
public abstract class AbstractProducer implements Runnable{

    @Override
    public final void run() {//不允许子类覆盖
        while (true){
            produce();//生产
        }
    }

    protected abstract void produce() ;
}
