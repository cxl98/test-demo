package com.cxl.strategy.A1;

import java.util.Random;

public class WinStrategy implements Strategy {
    private Random random;
    private boolean won=false;
    private Hand prevHand;
    public WinStrategy() {
        random = new Random();
    }

    @Override
    public Hand nextHand() {
        if (!won) {
            prevHand=Hand.getHand(random.nextInt(3));
        }
        return prevHand;
    }

    @Override
    public void study(boolean win) {
        won=win;
    }
}
