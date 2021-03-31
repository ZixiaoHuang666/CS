package com.Huang.lesson4;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageIconDemo extends JFrame {
    public ImageIconDemo() {
        //获取图片地址
        URL url = ImageIconDemo.class.getResource("11.jpeg");
        JLabel label = new JLabel("ImageIcon");
        ImageIcon imageIcon = new ImageIcon(url);
        label.setIcon(imageIcon);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        Container contentPane = getContentPane();
        contentPane.setVisible(true);
        contentPane.add(label);
        setBounds(100,100,200,300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ImageIconDemo();
    }
}

