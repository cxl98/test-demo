package com.cxl.consistenthashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class ConsistentHashing {
    //物理节点
    private Set<String> pNodes = new TreeSet<>();
    // 虚拟节点的复制倍数
    private final int COPY_VALUES =16;
    //虚拟节点　 哈希值 => 物理节点
    private TreeMap<Long, String> vNodes = new TreeMap<>();

    //默认初始化物理节点
    public ConsistentHashing() {
        pNodes.add("192.168.56.10");
        pNodes.add("192.168.56.11");
        pNodes.add("192.168.56.12");
        pNodes.add("192.168.56.13");
        Map();
    }

    public ConsistentHashing(Set<String> pNodes) {
        this.pNodes = pNodes;
    }

    // 32位的 Fowler-Noll-Vo 哈希算法
    private Long FNVHash(String key) {
        final int p = 16777619;
        long hash = 21666136261L;
        for (int i = 0, num = key.length(); i < num; ++i) {
            hash = (hash ^ key.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }
    private long hash(String key){

        //md5 byte
        MessageDigest md5;

        try {
            md5=MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported",e);
        }
        md5.reset();
        byte[] keyBytes;
        keyBytes=key.getBytes(StandardCharsets.UTF_8);
        md5.update(keyBytes);
        byte[] digest=md5.digest();

        // 哈希码，截断为32位
        long hashCode=((long)(digest[3] & 0xFF)<<24) |((long)(digest[2]& 0xFF)<<16) |((long)(digest[1] & 0xFF)<<8)|(digest[0] &0xFF);
        return hashCode &0xffffffffL;
    }
    // 根据物理节点，构建虚拟节点映射表
    public void Map() {
        for (String node : pNodes) {
            addPNodes(node);
        }
    }

    // 添加物理节点
    public void addPNodes(String node) {
        for (int i = 0; i < COPY_VALUES; i++) {
            Long aLong = hash(node + "_" + i);
            vNodes.put(aLong, node);
        }
    }

    // 删除物理节点
    public void removePNodes(String node) {
        for (int i = 0; i < COPY_VALUES; i++) {
            Long aLong = hash(node + "_" + i);
            vNodes.remove(aLong);
        }
    }

    //查找对象(数据)映射的节点
    public String getDataNode(String object) {
        Long aLong = hash(object);
        SortedMap<Long, String> map = vNodes.tailMap(aLong);
        Long key = map.isEmpty() ? vNodes.firstKey() : map.firstKey();
        return vNodes.get(key);
    }

    //统计对象(数据)与节点的映射关系
    public void dumpDataNodeMap(int min, int max) {
        Map<String, Integer> dataNodeMap = new TreeMap<>();
        for (int i = min; i <= max; i++) {
            String dataNode = getDataNode(Integer.toString(i));
            Integer integer = dataNodeMap.get(dataNode);
            dataNodeMap.put(dataNode, (integer == null ? 0 : integer + 1));
        }

        int totalCount = max - min + 1;
        for (Map.Entry<String, Integer> entry : dataNodeMap.entrySet()) {
            long i = 100 * entry.getValue() / totalCount;
            System.out.println("IP=====" + entry.getKey() + " :RATE" + i + "%");
        }
    }

    public static void main(String[] args) {
        ConsistentHashing ch=new ConsistentHashing();
        long l = System.currentTimeMillis();
        System.out.println("默认情况");
        ch.dumpDataNodeMap(0,65535);
        System.out.println("================");
        ch.removePNodes("192.168.56.10");
        ch.dumpDataNodeMap(0,65535);
        System.out.println("--------------");
        ch.addPNodes("192.168.56.14");
        ch.dumpDataNodeMap(0,65535);
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
    }


}
