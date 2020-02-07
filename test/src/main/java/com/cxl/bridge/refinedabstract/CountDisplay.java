package com.cxl.bridge.refinedabstract;

import com.cxl.bridge.abstraction.Display;
import com.cxl.bridge.implementor.DisplayImpl;

public class CountDisplay extends Display {

    public CountDisplay(DisplayImpl display) {
        super(display);
    }

    public void multiDisplay(int size){
        open();
        for (int i = 0; i < size; i++) {
            System.out.print(i+":");
            print();
        }
        close();
    }
}
