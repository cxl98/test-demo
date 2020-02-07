package com.cxl.bridge.refinedabstract;

import com.cxl.bridge.implementor.DisplayImpl;

import java.util.Random;

public class RandDispaly extends CountDisplay{
    private Random random=new Random();

    public RandDispaly(DisplayImpl display) {
        super(display);
    }

    public void randDisplay(int times){
        multiDisplay(random.nextInt(times));
    }
}
