package com.Huang.lesson2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestCalc {
    public static void main(String[] args) {
        new Calculator().LoadFrame();
    }
}
//Frame类
class Calculator extends Frame{

    TextField textField1,textField2,textField3;

    public void LoadFrame(){
        //3个文本框一个按钮
        textField1 = new TextField(10);
        textField2 = new TextField(10);
        textField3 = new TextField(20);//最大字符数
        Button button1 = new Button("=");
        //一个标签，界面上的文字提示
        Label label = new Label("+");
        button1.addActionListener(new MyCalculator());
        //布局
        setLayout(new FlowLayout());
        add(textField1);
        add(label);
        add(textField2);
        add(button1);
        add(textField3);
        pack();
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
  private class MyCalculator implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //获得加数和被加数
            int n1 = Integer.parseInt(textField1.getText());
            int n2 = Integer.parseInt(textField2.getText());
            //加法运算后，放到第三个框
            int n3 = n1+n2;
            //清除前两个框
            textField1.setText("");
            textField2.setText("");
            textField3.setText(n3+"");
        }
    }
}

