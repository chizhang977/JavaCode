package com.cheng.jihekuangjia;

import jdk.nashorn.internal.ir.IdentNode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *HashMap
 *  1、数据结构
 *      数组+链表(java7),数组+链表+红黑树
 *
 * 注意：
 * hashCode()方法决定了对象会被放到哪个bucket里，当多个对象的哈希值冲突时
 * equals()方法决定了这些对象是否是“同一个对象”。所以，如果要将自定义的对象放入到HashMap或HashSet中，
 * 需要**@Override** hashCode()和equals()方法。
 *
 */
public class HashSetHashMap {
    public static void main(String[] args) {

        HashMap<Object,Object> map = new HashMap<>();

        map.put(null,null);//key和value都可以为null，但是key是不可以相同的
        map.put("name","cheng");
        map.put("age",23);
        map.put("sex",true);

        for (Map.Entry<Object,Object> entry : map.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }//不同的时间迭代HashMap,顺序是不同的


    }
}
