package com.cxl.strategy.A2;

import java.util.Random;

public class WinStrategy implements Strategy1 {
    private Random random;
    private boolean won=false;
    private Hand1 prevHand;
    public WinStrategy() {
        random = new Random();
    }


    public Hand1 nextHand() {
        if (!won) {
            prevHand=Hand1.getHand(random.nextInt(3));
        }
        return prevHand;
    }

    public void study(boolean win) {
        won=win;
    }


}
