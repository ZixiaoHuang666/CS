package com.Huang.lesson5;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class JButtonDemo02 extends JFrame{
    public JButtonDemo02(){
        Container container = getContentPane();
        URL resource = JButtonDemo01.class.getResource("2.jfif");
        Icon icon = new ImageIcon(resource);
       //单选框
        JRadioButton jRadioButton1 = new JRadioButton("01");
        JRadioButton jRadioButton2 = new JRadioButton("02");
        JRadioButton jRadioButton3 = new JRadioButton("03");

        //单选框只能选择一个，分组,每个组织只能选择一个
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);

        container.add(jRadioButton1, BorderLayout.CENTER);
        container.add(jRadioButton2, BorderLayout.SOUTH);
        container.add(jRadioButton3, BorderLayout.NORTH);



        setVisible(true);
        setSize(500,300);
    }

    public static void main(String[] args) {
        new JButtonDemo02();
    }
}
