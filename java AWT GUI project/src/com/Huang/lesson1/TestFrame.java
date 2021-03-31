package com.Huang.lesson1;
import java.awt.*;
/**
 * @author HuangZixiao
 */
public class TestFrame {
    public static void main(String[] args) {
        //Frame,JDK search original code
        Frame frame = new Frame("我的第一个java图形界面窗口");
        //set the visibility
        frame.setVisible(true);
        //set the size
        frame.setSize(400,400);
        //set the color
        //see Color() oc
        frame.setBackground(new Color(14, 180, 187));
        //set the pop location
        frame.setLocation(450,170);
        //set the size fixed
        frame.setResizable(false);
    }
}
