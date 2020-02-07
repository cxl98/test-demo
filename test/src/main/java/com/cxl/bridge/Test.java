package com.cxl.bridge;

import com.cxl.bridge.refinedabstract.CountDisplay;
import com.cxl.bridge.abstraction.Display;
import com.cxl.bridge.implementor.DisplayImpl;
import com.cxl.bridge.concreteimplementor.StringDisplayImpl;
import com.cxl.bridge.refinedabstract.RandDispaly;

public class Test {
    public static void main(String[] args) {
        DisplayImpl display=new StringDisplayImpl("Hello,World");
        Display display1=new Display(display);
        CountDisplay countDisplay=new CountDisplay(display);
        RandDispaly randDispaly=new RandDispaly(display);
        randDispaly.randDisplay(50);
//        System.out.println("==========================================================");
//        countDisplay.multiDisplay(5);
//        System.out.println("_-----------------------------------------------------------");
//        display1.display();
    }
}
