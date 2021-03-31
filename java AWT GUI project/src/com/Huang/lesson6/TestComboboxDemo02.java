package com.Huang.lesson6;

import javax.swing.*;
import java.awt.*;

public class TestComboboxDemo02 extends JFrame {
    public TestComboboxDemo02() {
        Container container = getContentPane();
        //生成内容内容
        String[] contents = {"1","2","3"};
        JList jList = new JList(contents);
        container.add(jList);

        setSize(500,350);
        setVisible(true);
    }
    public static void main(String[] args) {
        new TestComboboxDemo02();
    }
}
