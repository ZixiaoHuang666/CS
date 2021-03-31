package com.Huang.lesson6;

import com.Huang.lesson2.TestText01;

import javax.swing.*;
import java.awt.*;

public class TestTextDemo01 extends JFrame {
    public TestTextDemo01() {
        Container container = getContentPane();
        //生成内容内容
        JTextField textField = new JTextField("hello");
        JTextField textField2 = new JTextField("word",20);
        JPasswordField jPasswordField = new JPasswordField();

        container.add(textField, BorderLayout.SOUTH);
        container.add(textField2, BorderLayout.NORTH);
        container.add(jPasswordField, BorderLayout.CENTER);
        setSize(500,350);
        setVisible(true);
    }
    public static void main(String[] args) {
        new TestTextDemo01();
    }
}
