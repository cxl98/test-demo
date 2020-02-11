package com.cxl.strategy.A1;

public class Hand {
    private static final int HANDVALUE_GUU = 0;//表示石头的值
    private static final int HANDVALUE_CHO = 1;//表示剪刀的值
    private static final int HANDVALUE_PAA = 2;//表示布的值
    private int handValue;
    public static final Hand[] hand = {
            new Hand(HANDVALUE_GUU),
            new Hand(HANDVALUE_CHO),
            new Hand(HANDVALUE_PAA)
    };
    public static final String[] name = {"石头", "剪刀", "布"};

    public Hand(int handvalue) {
        this.handValue = handvalue;
    }

    public static Hand getHand(int handValue) {
        return hand[handValue];
    }

    public boolean isWin(Hand h) {
        return fight(h) == 1;
    }

    public boolean isLose(Hand hand) {
        return fight(hand) == -1;
    }

    private int fight(Hand h) {
        if (this == h) {
            return 0;
        } else if ((this.handValue + 1) % 3 == h.handValue) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return name[handValue];
    }
}
    

