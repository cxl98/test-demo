package com.cxl.ui;

import javax.swing.*;

public class File {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;

    public static void main(String[] args) {
        JFrame frame = new JFrame("File");
        frame.setContentPane(new File().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
