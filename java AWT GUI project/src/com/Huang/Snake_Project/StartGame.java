package com.Huang.Snake_Project;

import javax.swing.*;

public class StartGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(10,10,910,650);
        frame.setResizable(false);
        frame.add(new GamePanel());
        frame.setVisible(true);
    }
}
