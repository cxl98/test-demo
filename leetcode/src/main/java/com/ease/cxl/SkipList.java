package com.ease.cxl;

import java.util.Arrays;

public class SkipList {
    class Node {
        private int data;
        private Node forwards[] = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");
            return builder.toString();
        }
    }

    private static final float SKIPLIST_P = 0.5f;
    private static final int MAX_LEVEL = 16;//最大的Level
    private int levelCount = 1;
    private Node head = new Node();

    public Node find(int value) {
        Node p = new Node();
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    public void add(int value) {
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        Node add[] = new Node[level];
        for (int i = 0; i < level; i++) {
            add[i] = head;
        }
        //记录每个级别的最大值，该最大值小于update[]中的插入值
        Node p = head;
        for (int i = level - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            add[i] = p;//在搜索路径中使用更新保存节点
        }
        //在搜索路径节点中，下一个节点成为新节点forwords（下一个）
        for (int i = 0; i < level; i++) {
            newNode.forwards[i] = add[i].forwards[i];
            add[i].forwards[i] = newNode;
        }
        //更新节点高度
        if (levelCount < level) {
            levelCount = level;
        }
    }
    //删除节点
    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = new Node();
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; --i) {
                if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }
        while (levelCount > 1 && head.forwards[levelCount] == null) {
            levelCount--;
        }
    }

    //用 randomLevel 生成一个合理的层数
    private int randomLevel() {
        int level = 1;
        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL) {
            level += 1;
        }
        return level;
    }

    public void print() {
        Node p =head;
        while (p.forwards[0] != null) {
            System.out.println(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SkipList list=new SkipList();
        list.add(1);
        list.add(2);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(3);
        list.add(4);
        list.add(5);
        list.print();

        Node node = list.find(2);
        System.out.println(node);
    }
}
