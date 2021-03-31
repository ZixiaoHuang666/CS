package com.Huang.lesson3;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class TestMouseListener {
    public static void main(String[] args) {
        new MyFrame("画笔");
    }
}
class MyFrame extends Frame{
    //需要画笔，，需要集合存储点
    ArrayList<int[]> point;
    public MyFrame(String title) {
        super(title);
        setBounds(200,200,400,300);
        //鼠标针对窗口要在上面创建
        point = new ArrayList<>();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MyFrame frame = (MyFrame) e.getSource();
                //点击的时候加点
                frame.point.add(new int[]{e.getX(),e.getY()});
                frame.repaint();
            }
        });
        pack();
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    @Override
    public void paint(Graphics g){
        //画画需要监听鼠标的位置
        for(int[] d : point){
            g.setColor(Color.green);
            g.fillOval(d[0],d[1],10,10);
        }
    }

}
