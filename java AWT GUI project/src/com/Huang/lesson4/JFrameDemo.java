package com.Huang.lesson4;

import javax.swing.*;
import java.awt.*;

public class JFrameDemo {
    public void init() {
        JFrame jFrame = new JFrame("JFrame窗口");
        jFrame.setVisible(true);
        jFrame.setBounds(100,100,200,200);
        //jFrame.setBackground(Color.cyan);这样是无法变色的区别，要把容器读出来
        Container contentPane = jFrame.getContentPane();
        contentPane.setBackground(Color.blue);
        //设置文字JLable
        JLabel label1 = new JLabel("hihihi");
        //让文本居中
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        jFrame.add(label1);
        //关闭事件
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JFrameDemo().init();
    }
}
