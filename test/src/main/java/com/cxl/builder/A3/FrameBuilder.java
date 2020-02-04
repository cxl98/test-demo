package com.cxl.builder.A3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameBuilder extends Builder implements ActionListener{
    private JFrame frame=new JFrame();
    private Box box=new Box(BoxLayout.Y_AXIS);

    public void makeTitle(String title) {
        frame.setTitle(title);
    }

    public void makeString(String cxl) {
        box.add(new JLabel(cxl));
    }

    public void makeItem(String[] strings) {
        Box innerBox=new Box(BoxLayout.Y_AXIS);
        for (String item: strings) {
            JButton button=new JButton(item);
            button.addActionListener( this);
            innerBox.add(button);
        }
        box.add(innerBox);
    }

    public void close() {
        frame.getContentPane().add(box);
        frame.pack();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public JFrame getResult() {
        return frame;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
