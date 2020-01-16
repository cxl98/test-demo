package com.cxl.common;

import java.util.*;

/**
 * 用map来保存用户和socket输出流的对应关系
 * K是String类型的用户名,不可重复
 * V是socket返回的outputStream对象,不允许重复
 * @param <K>
 * @param <V>
 */
public class ChatMap<K,V> {
    public Map<K,V> map= Collections.synchronizedMap(new HashMap<K, V>());
    //根据outputStream对象删除
    public synchronized void removeByValue(Object value){
        for (Object key: map.keySet()) {
            if (map.get(key)==value){
                map.remove(key);
                break;
            }
        }
    }

    public synchronized Set<V> valueSet(){
        Set<V> set=new HashSet<V>();
        //遍历map,将map存进set
        for (K key: map.keySet()) {
            set.add(map.get(key));
        }
        return set;
    }

    public synchronized K getKeyByValue(V val){
        for (K key: map.keySet()) {
            if (map.get(key)==val||map.get(key).equals(val)){
                return key;
            }
        }
        return null;
    }
    public synchronized V put(K key,V value){
        for (V val: valueSet()) {
            if (val.equals(value)&&val.hashCode()==value.hashCode()){
               throw new RuntimeException("此输入流已经存在");
            }
        }
        return map.put(key,value);
    }
}
