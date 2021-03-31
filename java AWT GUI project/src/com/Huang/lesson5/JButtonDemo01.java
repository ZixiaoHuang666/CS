package com.Huang.lesson5;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class JButtonDemo01 extends JFrame {
    public JButtonDemo01() {
        Container container = getContentPane();
        //
        URL resource = JButtonDemo01.class.getResource("2.jfif");
        Icon icon = new ImageIcon(resource);
        JButton jButton = new JButton();
        jButton.setIcon(icon);
        jButton.setToolTipText("图片按钮");
        container.add(jButton);
        setVisible(true);
        setSize(500,300);


    }

    public static void main(String[] args) {
        new JButtonDemo01();

    }
}
