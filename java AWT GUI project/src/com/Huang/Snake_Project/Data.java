package com.Huang.Snake_Project;

import com.Huang.lesson4.ImageIconDemo;

import javax.swing.*;
import java.net.URL;

public class Data {
    public static URL headerURL = Data.class.getResource("static image/header.png");
    public static ImageIcon header = new ImageIcon(headerURL);
    public static URL bodyURL = Data.class.getResource("static image/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);
    public static URL downURL = Data.class.getResource("static image/down.png");
    public static ImageIcon down = new ImageIcon(downURL);
    public static URL foodURL = Data.class.getResource("static image/food.png");
    public static ImageIcon food = new ImageIcon(foodURL);
    public static URL leftURL = Data.class.getResource("static image/left.png");
    public static ImageIcon left = new ImageIcon(leftURL);
    public static URL rightURL = Data.class.getResource("static image/right.png");
    public static ImageIcon right = new ImageIcon(rightURL);
    public static URL upURL = Data.class.getResource("static image/up.png");
    public static ImageIcon up = new ImageIcon(upURL);
}
