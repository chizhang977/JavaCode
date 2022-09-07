package com.cheng.Thread.ProducerAndConsumer;


import java.util.concurrent.atomic.AtomicInteger;

//生产者生产当数据等于缓冲区最大值的时候，进行等待/阻塞
//此时就可通知消费者进行消费
public class Producer extends AbstractProducer{
    private DataBuffer dataBuffer;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public Producer(DataBuffer dataBuffer){
       this.dataBuffer = dataBuffer;
    }
    @Override
    protected void produce() {
        synchronized (dataBuffer.getDataBuffer()){
            while (dataBuffer.getDataBuffer().size() == DataBuffer.MAX_SIZE){//缓冲区满了
                try {
                    System.out.println("缓冲区满了,生产者阻塞等待......");
                    dataBuffer.getDataBuffer().wait();//进入等待状态
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //生产数据
            dataBuffer.getDataBuffer().add(atomicInteger.getAndIncrement());
            System.out.println("生产者生产了一条数据..");

            //通知消费者可以去消费
            dataBuffer.getDataBuffer().notifyAll();


        }
    }
}
