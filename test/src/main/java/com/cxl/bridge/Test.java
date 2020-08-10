package com.cxl.bridge;

import com.cxl.bridge.abstraction.Display;
import com.cxl.bridge.concrete.implementor.CharDisplayImpl;
import com.cxl.bridge.concrete.implementor.FileDisplayImpl;
import com.cxl.bridge.implementor.DisplayImpl;
import com.cxl.bridge.refinedabstract.CountDisplay;
import com.cxl.bridge.refinedabstract.IncreaseDisplay;

public class Test {
    public static void main(String[] args) {
//        DisplayImpl display=new StringDisplayImpl("Hello,World");
//        Display display1=new Display(display);
//        CountDisplay countDisplay=new CountDisplay(display);
//        RandDisplay randDispaly=new RandDisplay(display);
//        randDispaly.randDisplay(50);
//        System.out.println("==========================================================");
//        countDisplay.multiDisplay(5);
//        System.out.println("_-----------------------------------------------------------");
//        display1.display();

//        DisplayImpl display=new FileDisplayImpl("/home/cxl/cxl/test-demo/test/src/main/java/com/cxl/bridge/concreteimplementor/test.txt");
//        CountDisplay display1=new CountDisplay(display);
//        display1.multiDisplay(3);
        CharDisplayImpl charDisplay=new CharDisplayImpl("<","*",">");
        IncreaseDisplay increaseDisplay=new IncreaseDisplay(charDisplay,1);
        IncreaseDisplay increaseDisplay1=new IncreaseDisplay(new CharDisplayImpl("|","#","-"),2);
        increaseDisplay.increaseDisplay(4);
        increaseDisplay1.increaseDisplay(5);
    }
}
