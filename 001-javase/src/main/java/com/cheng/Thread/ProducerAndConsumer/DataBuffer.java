package com.cheng.Thread.ProducerAndConsumer;

import java.util.LinkedList;
import java.util.List;

//数据缓冲区
public class DataBuffer {

    private final List<Integer> dataBuffer = new LinkedList();
    protected  static final int MAX_SIZE = 10;

    public List<Integer> getDataBuffer(){
        return dataBuffer;
    }

}
