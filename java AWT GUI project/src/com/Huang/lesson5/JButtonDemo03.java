package com.Huang.lesson5;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class JButtonDemo03 extends JFrame {
    public JButtonDemo03(){
        Container container = getContentPane();
        URL resource = JButtonDemo01.class.getResource("2.jfif");
        Icon icon = new ImageIcon(resource);
        //单选框
        JRadioButton jRadioButton1 = new JRadioButton("01");
        JRadioButton jRadioButton2 = new JRadioButton("02");
        JRadioButton jRadioButton3 = new JRadioButton("03");

        //单选框只能选择一个，分组,每个组织只能选择一个
        JCheckBox jCheckBox = new JCheckBox("01");
        JCheckBox jCheckBox2 = new JCheckBox("02");
        container.add(jCheckBox,BorderLayout.NORTH);
        container.add(jCheckBox2,BorderLayout.SOUTH);



        setVisible(true);
        setSize(500,300);
    }

    public static void main(String[] args) {
        new JButtonDemo03();
    }
}
