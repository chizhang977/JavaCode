package com.cheng.jihekuangjia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * LinkedList:
 *  1、有序可重复，可以为null。
 *  2、底层数据结构为:双向链表，可以高效的增删元素
 *  3、
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");
        linkedList.add(null);
        linkedList.add(null);
        //返回第一个元素和最后一个元素
        System.out.println("第一个元素为:"+linkedList.getFirst());
        System.out.println("最后一个元素为:"+linkedList.getLast());
        //删除
        System.out.println("删除第一个:"+linkedList.removeFirst());
        System.out.println("删除最后一个:"+linkedList.removeLast());
        System.out.println("删除指定的:"+linkedList.remove("B"));
        System.out.println("删除指定索引："+linkedList.remove(1));

        //添加
        linkedList.add(1,"cheng");
        List list  = new ArrayList();
        list.add("1");list.add("2");
        linkedList.addAll(2,list);
        System.out.println("======================================");
        ListIterator<String> iterator = linkedList.listIterator(0);
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
        linkedList.clear();
    }
}
