package com.Huang.lesson4;

import javax.swing.*;
import java.awt.*;

public class JFrameDemo02 {
    public static void main(String[] args) {
        new MyJFrame2().init();
    }
}
class MyJFrame2 extends JFrame {
    public MyJFrame2(){
    }

    public void init() {
        //获得一个容器
        setBounds(10,10,200,300);
        setVisible(true);
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.blue);
    }
}
