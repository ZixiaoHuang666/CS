package com.Huang.lesson2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestAction2 {
    //两个按钮实现同一个监听
    //通过重写一个类可以使得多个按钮进行相似的监听
    public static void main(String[] args) {
        Frame frame = new Frame("开始停止");
        Button button1 = new Button("start");
        Button button2 = new Button("stop");
        button1.setActionCommand("button1-start");
        button2.setActionCommand("button2-stop");
        button1.addActionListener(new MyMonitor());
        button2.addActionListener(new MyMonitor());
        frame.add(button1,BorderLayout.NORTH);
        frame.add(button2,BorderLayout.SOUTH);
        frame.pack();;
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}
class MyMonitor implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("按钮被点击了：msg"+e.getActionCommand());
    }
}