package com.Huang.lesson5;

import javax.swing.*;
import java.awt.*;

public class JscrollDemo extends JFrame {
    public JscrollDemo() {
        Container contentPane = this.getContentPane();

        //文本域
        JTextArea jTextArea = new JTextArea(20, 50);
        jTextArea.setText("nihaonihao");
        //Scroll panel
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        contentPane.add(jScrollPane);
        setVisible(true);
        setBounds(100,100,300,350);

    }

    public static void main(String[] args) {
        new JscrollDemo();

    }
}
