package com.Huang.lesson1;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//Panel can be seen as space, but it is dependent on frame;
public class TestPanel {
    public static void main(String[] args) {
        Frame frame = new Frame();
        //panel has an idea called Flowlayout
        Panel panel = new Panel();
        frame.setLayout(null);
        frame.setBounds(300,170,500,500);
        frame.setBackground(new Color(105, 187, 177));
        //set the panel location relative to frame
        panel.setBounds(50,50,400,400);
        //panel is inside of frame
        panel.setBackground(new Color(255,255,255));
        frame.add(panel);
        frame.setVisible(true);
        //monitor the event
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
