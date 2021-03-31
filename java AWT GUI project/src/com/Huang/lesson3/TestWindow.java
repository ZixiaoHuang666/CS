package com.Huang.lesson3;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestWindow {
    public static void main(String[] args) {
        new WindowFrame();
    }
}
class WindowFrame extends Frame {
    public WindowFrame(){
        setBackground(Color.blue);
        setBounds(100,100,200,200);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                System.exit(0);
            }

            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("window open");
            }
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("window closed");
            }
            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("window activated");
            }

        });
        setVisible(true);
    }

}
