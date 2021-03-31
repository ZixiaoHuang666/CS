package com.Huang.lesson5;

import javax.swing.*;
import java.awt.*;

public class JpanelDemo extends JFrame {
    public JpanelDemo() {
        Container container = getContentPane();
        container.setLayout(new GridLayout(2,1,10,10));//后两个是面板间距
        JPanel panel1 = new JPanel(new GridLayout(1,3));
        JPanel panel2 = new JPanel(new GridLayout(1,2));
        panel2.add(new JButton("2"));
        panel2.add(new JButton("2"));
        panel1.add(new JButton("1"));
        panel1.add(new JButton("1"));
        panel1.add(new JButton("1"));
        setVisible(true);
        setSize(500,500);
        container.add(panel1);
        container.add(panel2);




    }

    public static void main(String[] args) {
        new JpanelDemo();
    }
}
