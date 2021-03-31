package com.Huang.lesson2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestText01 {
    public static void main(String[] args) {
        new MyFrame();
    }
}
class MyFrame extends Frame{
    public MyFrame(){
        TextField textField = new TextField();
        add(textField);
        //监听文本框输入
        textField.addActionListener(new MyActionListener());
        pack();
        setVisible(true);
        //设置替换编码，比如密码隐藏
        textField.setEchoChar('*');
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}
class MyActionListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {

        TextField field = (TextField)e.getSource();//获得一些资源返回一个Object
        System.out.println(field.getText());//按下回车就会触发这个输入框
        field.setText("");

    }
}

