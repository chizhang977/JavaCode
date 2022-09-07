package com.cheng.Thread.ProducerAndConsumer;

public class Test {
    public static void main(String[] args) {
        DataBuffer dataBuffer = new DataBuffer();

        for (int i = 0; i < 16; i++) {
            Producer producer = new Producer(dataBuffer);
            Thread thread = new Thread(producer);
            thread.start();
        }


        for (int i = 0; i < 16; i++) {
            Consumer consumer = new Consumer(dataBuffer);
            Thread thread1 = new Thread(consumer);
            thread1.start();
        }

    }
}
