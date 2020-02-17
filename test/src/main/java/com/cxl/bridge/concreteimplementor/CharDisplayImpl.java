package com.cxl.bridge.concreteimplementor;

import com.cxl.bridge.implementor.DisplayImpl;

public class CharDisplayImpl extends DisplayImpl {
    private String head;
    private String body;
    private String foot;

    public CharDisplayImpl(String head, String body, String foot) {
        this.head = head;
        this.body = body;
        this.foot = foot;
    }

    @Override
    public void rawOpen() {
        System.out.print(head);
    }

    @Override
    public void rawPrint() {
        System.out.print(body);
    }

    @Override
    public void rawClose() {
        System.out.println(foot);
    }
}
