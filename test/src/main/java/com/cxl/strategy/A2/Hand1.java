package com.cxl.strategy.A2;


public enum Hand1 {
    HANDVALUE_GUU("石头", 0),
    HANDVALUE_CHO("剪刀", 1),
    HANDVALUE_PAA("布", 2);
    private String name;
    private int value;

    Hand1(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static Hand1 getHand(int value) {
        switch (value) {
            case 0:
                return Hand1.HANDVALUE_GUU;
            case 1:
                return Hand1.HANDVALUE_CHO;
            case 2:
                return Hand1.HANDVALUE_PAA;
            default:
                return null;
        }
    }

    public int fight(Hand1 hand) {
        if (this == hand) {
            return 0;
        } else if ((this.value + 1) % 3 == hand.value) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public String toString() {
        return "Hand1{" +
                "name='" + name +"\'"+
                '}';
    }
}
