package com.cxl.reboot;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Reboot extends JComponent {
    public static void main(String[] args) throws IOException {
        int read = System.in.read();
        System.out.println(read);
    }
}