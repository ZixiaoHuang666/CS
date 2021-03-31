package com.Huang.lesson1;
import java.awt.*;
public class TestFrame2 {
    public static void main(String[] args) {
        //how to show multiple windows
        MyFrame window1 = new MyFrame(100,100,200,200,Color.blue);//Ctrl+D复制
        MyFrame window2 = new MyFrame(300,100,200,200,Color.yellow);//Ctrl+D复制
        MyFrame window3 = new MyFrame(100,300,200,200,Color.red);//Ctrl+D复制
        MyFrame window4 = new MyFrame(300,300,200,200,Color.magenta);//Ctrl+D复制
    }
}
class MyFrame extends Frame{
    static int id = 0;// it may exists multiple windows and we need a identifier
    public MyFrame(int x, int y, int w, int h, Color color) {
        super("Myframe"+(++id));
        setVisible(true);
        setBounds(x,y,w,h);
        setBackground(color);
    }
}
