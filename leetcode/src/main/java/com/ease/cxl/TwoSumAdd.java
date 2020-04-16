package com.ease.cxl;

public class TwoSumAdd {
    private int value;

    private TwoSumAdd next;

    public TwoSumAdd(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TwoSumAdd getNext() {
        return next;
    }

    public void setNext(TwoSumAdd next) {
        this.next = next;
    }

    public static TwoSumAdd twoSumAdd(TwoSumAdd node1,TwoSumAdd node2){
        TwoSumAdd node=new TwoSumAdd(0);
        TwoSumAdd p=node1,q=node2,curr=node;
        int carry=0;
        while(null!=p||q!=null){
            int x=(p!=null)?p.value:0;
            int y=(q!=null)?q.value:0;
            int sum=carry+x+y;
            curr.next=new TwoSumAdd(sum%10);
            curr=curr.next;
            if (p!=null){
                p=p.next;
            }
            if (q!=null){
                q=q.next;
            }

        }
        if (carry>0){
            curr.next=new TwoSumAdd(carry);
        }
        return node.next;
    }

    public static void main(String[] args) {
        TwoSumAdd twoSumAdd=new TwoSumAdd(0);
        twoSumAdd.next.value=4;
        twoSumAdd.next.next.value=3;

        TwoSumAdd twoSumAdd1=new TwoSumAdd(5);
        twoSumAdd1.next.value=6;
        twoSumAdd1.next.next.value=4;
        TwoSumAdd twoSumAdd2 = twoSumAdd(twoSumAdd, twoSumAdd1);
        while(twoSumAdd2!=null){
            System.out.println(twoSumAdd2.value);
            twoSumAdd2=twoSumAdd2.next;
        }
    }
}
