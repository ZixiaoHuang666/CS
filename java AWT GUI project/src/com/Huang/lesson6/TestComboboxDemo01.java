package com.Huang.lesson6;

import javax.swing.*;
import java.awt.*;

public class TestComboboxDemo01 extends JFrame {
    public TestComboboxDemo01() {
        Container container = getContentPane();
        JComboBox<Object> status = new JComboBox<>();
        status.addItem("0");
        status.addItem("1");
        status.addItem("2");
        status.addItem("3");
        container.add(status);
        setSize(500,350);
        setVisible(true);
    }
    public static void main(String[] args) {
        new TestComboboxDemo01();
    }
}
