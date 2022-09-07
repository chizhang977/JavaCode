package com.cheng.Thread.ProducerAndConsumer;

//消费者：去缓冲去中获得数据，将缓冲区中的数据删除
public class Consumer extends AbastractConsumer{
    private DataBuffer dataBuffer;

    public Consumer(DataBuffer dataBuffer) {
        this.dataBuffer = dataBuffer;
    }

    @Override
    protected void consumer()  {
        synchronized (dataBuffer.getDataBuffer()){
            while (dataBuffer.getDataBuffer().isEmpty()){//缓冲区为空，消费者等待，通知生产者进行生产
                try {
                    System.out.println("缓冲区空了，消费者进行等待....");
                    dataBuffer.getDataBuffer().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

//         消费者进行消费
            Integer remove = dataBuffer.getDataBuffer().remove(0);
            System.out.println("消费者消费了一条数据.."+remove);

//            通知生产者进行生产
            dataBuffer.getDataBuffer().notifyAll();

        }

    }
}
