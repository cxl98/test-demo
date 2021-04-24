package com.cxl.algorithm;

public class SumAdd {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //    static class Solution {
//        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//            ListNode listNode = new ListNode(0);
//            ListNode p = l1, q = l2, cur = listNode;
//            int carry = 0;
//            while (p != null || q != null) {
//                int x = (p != null) ? p.val : 0;
//                int y = (q != null) ? q.val : 0;
//                int sum = carry + x + y;
//                carry = sum / 10;
//                cur.next = new ListNode(sum % 10);
//                cur = cur.next;
//                if (p != null) {
//                    p = p.next;
//                }
//                if (q != null) {
//                    q = q.next;
//                }
//            }
//            if (carry > 0) {
//                cur.next = new ListNode(carry);
//            }
//            return listNode.next;
//        }
    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode listNode = new ListNode(0);
            ListNode p = l1, q = l2, cur = listNode;
            int target = 0;
            while (null != q || null != p) {
                int x = (q != null) ? q.val : 0;
                int y = (p != null) ? p.val : 0;
                int sum=target+x+y;
                target=sum/10;
                cur.next=new ListNode(sum%10);
                cur=cur.next;
                if (q!=null){
                    q=q.next;
                }
                if (p!=null){
                    p=p.next;
                }
            }
            if (0<target){
                cur.next=new ListNode(target);
            }
            return listNode.next;
        }
    }

    public static void main(String[] args) {
//        l1 = [2,4,3], l2 = [5,6,4]
        ListNode listNode = new ListNode(2);
        ListNode listNode1 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        listNode.next = listNode1;
        listNode1.next = listNode3;
        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(4);
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        Solution solution = new Solution();
        ListNode listNode2 = solution.addTwoNumbers(listNode, listNode4);
        while (listNode2 != null) {
            System.out.println(listNode2.val);
            listNode2 = listNode2.next;
        }
    }
}
