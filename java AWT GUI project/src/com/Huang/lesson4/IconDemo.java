package com.Huang.lesson4;

import javax.swing.*;
import java.awt.*;

public class IconDemo extends JFrame implements Icon {
    private int width;
    private int height;

    public IconDemo() {
    }
    public IconDemo(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void init(){
        IconDemo iconDemo = new IconDemo(15, 15);
        //图标放在标签上，也可以放在按钮上
        JLabel iconTest = new JLabel("iconTest", iconDemo, SwingConstants.CENTER);
        Container contentPane = getContentPane();
        contentPane.add(iconTest);
        setVisible(true);
    }
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.fillOval(x,y,width,height);
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }

    public static void main(String[] args) {
        new IconDemo().init();
    }
}
