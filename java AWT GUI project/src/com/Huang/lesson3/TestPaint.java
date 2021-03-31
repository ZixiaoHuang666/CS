package com.Huang.lesson3;
import java.awt.*;
public class TestPaint {
    public static void main(String[] args) {
        new MyPaint().loadFrame();
    }
}
class MyPaint extends Frame{

    public void loadFrame(){
        setBounds(200,150,600,500);
        setVisible(true);
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.drawOval(100,100,100,100);//空心圆fillOval实心
        //养成习惯，画笔用完，将它还原成最初的颜色
    }
}
