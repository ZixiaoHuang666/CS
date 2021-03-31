package com.Huang.lesson1;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestFlowLayout {
    public static void main(String[] args) {
        Frame frame = new Frame();
        //Component_button
        Button button1 = new Button("button1");
        Button button2 = new Button("button2");
        Button button3 = new Button("button3");
        //set the layout to FlowLayout default center
        //frame.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.setLayout(new FlowLayout());
        //frame.setLayout(new FlowLayout(FlowLayout.RIGHT));
        frame.setSize(200,200);
        frame.setVisible(true);
        //add the button
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
