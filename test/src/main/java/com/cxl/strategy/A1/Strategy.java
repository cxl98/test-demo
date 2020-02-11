package com.cxl.strategy.A1;

import com.cxl.strategy.A1.Hand;

public interface Strategy {
    Hand nextHand();
    void study(boolean win);
}
