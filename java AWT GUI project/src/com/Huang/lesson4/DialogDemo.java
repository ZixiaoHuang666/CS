package com.Huang.lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogDemo extends JFrame {
    public DialogDemo() {
        setVisible(true);
        setSize(700,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        //绝对布局,按坐标定死
        contentPane.setLayout(null);
        //
        JButton jButton = new JButton("点击弹出一个对话框");
        jButton.setBounds(30,30,200,50);
        //点击这个按钮的时候弹出一个弹窗
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //弹窗
                new MyDialog();

            }
        });
        contentPane.add(jButton);


    }

    public static void main(String[] args) {
        new DialogDemo();
    }
}
//需要两个窗口，弹窗窗口和主窗口
class MyDialog extends JDialog{
    public MyDialog() {
        super();
        Container contentPane = this.getContentPane();
        setBounds(100,100,500,500);
        contentPane.setBackground(Color.green);
        //contentPane.setLayout(null);
        contentPane.add(new JLabel("啦啦啦啦我是卖报的小行家"));
        //contentPane.setVisible(true);
        setVisible(true);
    }
}