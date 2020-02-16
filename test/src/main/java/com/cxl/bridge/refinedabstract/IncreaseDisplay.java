package com.cxl.bridge.refinedabstract;

import com.cxl.bridge.abstraction.Display;
import com.cxl.bridge.implementor.DisplayImpl;

public class IncreaseDisplay extends CountDisplay {
    private int step;
    public IncreaseDisplay(DisplayImpl display,int step) {
        super(display);
        this.step=step;
    }
    public void increaseDisplay(int tiems){
        int count=0;
        for (int i = 0; i <tiems ; i++) {
            multiDisplay(count);
            count+=step;
        }
    }
}
