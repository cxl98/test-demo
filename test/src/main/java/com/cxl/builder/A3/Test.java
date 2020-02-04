package com.cxl.builder.A3;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        FrameBuilder frameBuilder=new FrameBuilder();
        Director director=new Director(frameBuilder);
        director.construct();
        JFrame frame=frameBuilder.getResult();
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setLocation(500,500 );
    }
}
