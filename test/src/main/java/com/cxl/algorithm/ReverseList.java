package com.cxl.algorithm;

public class ReverseList {
    static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val=val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseList(ListNode listNode){
        if (listNode==null) return null;
        ListNode newList=null;
        while(listNode!=null){
            ListNode next=listNode.next;
            listNode.next=newList;
            newList=listNode;
             listNode=next;
        }
        return newList;
    }

    public static void main(String[] args) {
        ListNode listNode=new ListNode(1);
        ListNode listNode1=new ListNode(2);
        ListNode listNode2=new ListNode(3);
        ListNode listNode3=new ListNode(4);
        ListNode listNode4=new ListNode(5);
        listNode.next=listNode1;
        listNode1.next=listNode2;
        listNode2.next=listNode3;
        listNode3.next=listNode4;
        listNode4.next=null;
        ReverseList reverseList = new ReverseList();
        ListNode listNode5 = reverseList.reverseList(listNode);
        while(listNode5!=null){
            System.out.println(listNode5.val);
            listNode5=listNode5.next;
        }

    }
}
