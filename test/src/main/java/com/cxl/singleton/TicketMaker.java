package com.cxl.singleton;

public class TicketMaker {
    private static TicketMaker singleton=new TicketMaker() ;
    private int number = 1000;

    private TicketMaker() {
    }

    public static TicketMaker getSingleton() {
        return singleton;
    }

    public synchronized int getNextNumber() {
        return number++;
    }
}
