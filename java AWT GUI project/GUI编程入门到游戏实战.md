# GUI编程入门到游戏实战

告诉大家该怎么学？

- 是什么？
- 怎么玩
- 如何去在平时运用

组件

- 窗口
- 弹窗
- 面板
- 文本框
- 列表框
- 按钮
- 图片
- 监听事件
- 鼠标
- 键盘事件

## 1. 简介

Gui的核心技术：Swing，AWT这两个类，之所以不流行是因为界面不美观并且需要jre环境，我不能因为玩一个贪吃蛇，下整个Java环境

为什么我们要学习呢？MVC的基础，并且可以写出自己想要的一些小工具

工作时候可能维护到swing界面几率极小

了解MVC架构，了解监听

## 2.AWT(Abstract Window tool)

### 2.1 AWT介绍

1. 包含了很多类和接口，用于图形用户界面编程
2. 元素：窗口，按钮，文本框
3. 包继承顺序

![AWT](https://img.geek-docs.com/java/tutorials/43caf6d62a6bd94f418931c00c7d74cc.jpg)

### 2.2 组件和容器

#### 1 Frame

```java
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
```

<img src="C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210328210656959.png" alt="image-20210328210656959" style="zoom:50%;" />

问题：发现窗口无法关闭

此处回顾封装，我们用刚刚那么多过程创建了一个窗口怎样可以高效的创建多个窗口，自己开发一个工具类继承Frame类。

```java
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
```

<img src="C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210328212202028.png" alt="image-20210328212202028" style="zoom:50%;" />

#### 2 Panel

解决了关闭事件

```java
package com.Huang.lesson1;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//Panel can be seen as space, but it is dependent on frame;
public class TestPanel {
    public static void main(String[] args) {
        Frame frame = new Frame();
        //panel has an idea called Flowlayout
        Panel panel = new Panel();
        frame.setLayout(null);
        frame.setBounds(300,170,500,500);
        frame.setBackground(new Color(105, 187, 177));
        //set the panel location relative to frame
        panel.setBounds(50,50,400,400);
        //panel is inside of frame
        panel.setBackground(new Color(255,255,255));
        frame.add(panel);
        frame.setVisible(true);
        //monitor the event
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
```

<img src="C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210328213948232.png" alt="image-20210328213948232" style="zoom:50%;" />

### 2.3 布局管理器

- 在刚刚的例子中我们将FlowLayout设置为null

  - 流式布局

    ~~~java
    package com.Huang.lesson1;
    import java.awt.*;
    import java.awt.event.WindowAdapter;
    import java.awt.event.WindowEvent;
    
    public class TestFlowLayout {
        public static void main(String[] args) {
            Frame frame = new Frame();
            //Component_button
            Button button1 = new Button("button1");
            Button button2 = new Button("button2");
            Button button3 = new Button("button3");
            //set the layout to FlowLayout default center
            //frame.setLayout(new FlowLayout(FlowLayout.LEFT));//按钮向左对齐
            frame.setLayout(new FlowLayout());
            //frame.setLayout(new FlowLayout(FlowLayout.RIGHT));//按钮向右对齐
            frame.setSize(200,200);
            frame.setVisible(true);
            //add the button
            frame.add(button1);
            frame.add(button2);
            frame.add(button3);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        }
    }
    ~~~

    ![image-20210328215521956](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210328215521956.png)

  - 东西南北中

    ~~~java
    package com.Huang.lesson1;
    import java.awt.*;
    import java.awt.event.WindowAdapter;
    import java.awt.event.WindowEvent;
    
    public class TestBoarderLayout {
        public static void main(String[] args) {
            Frame frame = new Frame("Test Boarder Layout");
            Button east = new Button("east");
            Button west = new Button("west");
            Button south = new Button("south");
            Button north = new Button("north");
            Button center = new Button("center");
    
            frame.add(east,BorderLayout.EAST);
            frame.add(west,BorderLayout.WEST);
            frame.add(south,BorderLayout.SOUTH);
            frame.add(north,BorderLayout.NORTH);
            frame.add(center,BorderLayout.CENTER);
    
            frame.setSize(200,200);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        }
    }
    ~~~

    ![image-20210328220350211](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210328220350211.png)

    

  - 表格类布局Grid

    ~~~java
    package com.Huang.lesson1;
    
    import java.awt.*;
    import java.awt.event.WindowAdapter;
    import java.awt.event.WindowEvent;
    
    public class TestGridLayout {
        public static void main(String[] args) {
            Frame frame = new Frame("Test Grid Layout");
            Button btn1 = new Button("btn1");
            Button btn2 = new Button("btn2");
            Button btn3 = new Button("btn3");
            Button btn4 = new Button("btn4");
            Button btn5 = new Button("btn5");
            Button btn6 = new Button("btn6");
            frame.setLayout(new GridLayout(3,2));
            frame.add(btn1);
            frame.add(btn2);
            frame.add(btn3);
            frame.add(btn4);
            frame.add(btn5);
            frame.add(btn6);
            //frame.setSize(200,200);
            frame.pack();//加上以后自动布局，自动填充大小
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        }
    }
    ~~~

    ![image-20210328221107668](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210328221107668.png)

  总结：

  1. Frame是一个顶级窗口
  2. Panel无法单独显示，必须添加到某个容器中
  3. 布局管理器三种，流式，东西南北中，表格
  4. 大小，定位，背景颜色，可见性，监听事件

### 2.4事件监听

当某个事件发生的时候做什么,如按下某个按钮，关闭窗口

~~~java
package com.Huang.lesson2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestActiveEvent {
    public static void main(String[] args) {
        //按下按钮，触发事件
        Frame frame = new Frame("你好");
        Button button = new Button("你好");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("HI");
            }
        });
        frame.add(button);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
~~~

如果想要多个按钮执行同一类监听事件，可以考虑自己再继承写一个类，进一步抽象功能

```java
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
```

### 2.5.输入框TextField监听

怎么创造一个输入框，怎么替换成密码编码，怎么输入后清空，同时后台可以获取输入数据

~~~java
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
~~~

### 2.6 简易计算器，组合+内部类回顾复习

OOP原则：组合，大于继承

~~~java
class A extends B //继承
class A{
    public B b  //组合
}
~~~

---

完全改造成面向对象想法，并不是进行值传递，而是封装好后直接扔给功能类

~~~java
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
        button1.addActionListener(new MyCalculator(this));//ood思想的体现
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
}
//监听器类
class MyCalculator implements ActionListener {
    private Calculator calculator;
    //获取三个变量
    public MyCalculator(Calculator calculator){
        this.calculator = calculator;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //获得加数和被加数
        int n1 = Integer.parseInt(calculator.textField1.getText());
        int n2 = Integer.parseInt(calculator.textField2.getText());
        //加法运算后，放到第三个框
        int n3 = n1+n2;
        //清除前两个框
        calculator.textField1.setText("");
        calculator.textField2.setText("");
        calculator.textField3.setText(n3+"");
    }
}
~~~

进一步会发现内部类可能是更好的写法，内部类最大的好处即可以畅通无阻的访问外部的属性和方法

~~~java
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
~~~

### 2.7 画笔

在frame上绘画图形

~~~java
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
~~~

### 2.8鼠标监听

目的是实现鼠标点击，在画板上出现一个点

我们有四个东西，画板，画笔，存点的集合，鼠标，画板和画笔是绑定的，即在frame中重写paint方法

鼠标监听事件，每次点下我们要存点进集合，并且调用画笔重新绘制点集

~~~java
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
~~~

### 2.9 窗口监听

~~~java
package com.Huang.lesson3;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestWindow {
    public static void main(String[] args) {
        new WindowFrame();
    }
}
class WindowFrame extends Frame {
    public WindowFrame(){
        setBackground(Color.blue);
        setBounds(100,100,200,200);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                System.exit(0);
            }

            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("window open");
            }
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("window closed");
            }
            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("window activated");
            }

        });
        setVisible(true);
    }

}
~~~

### 2.10 键盘监听

监听按下的是哪个键

~~~java
package com.Huang.lesson3;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestKeyListener {
    public static void main(String[] args) {
        new KeyFrame();
    }
}
class KeyFrame extends Frame {
    public KeyFrame() {
        setBounds(1,2,300,400);
        setVisible(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_UP){
                    System.out.println("按上键");
                }
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               System.exit(0);
            }
        });
    }
}
~~~

## 3 Swing

Swing可以看成AWT包的升级版，提供了更强大的功能和界面

### 3.1 窗口，面板

Swing类调用和AWT基本一致，区别在于JFrame无法直接setBackground为相应的颜色，而是应该先Container contentPane = jFrame.getContentPane()获取容器，再改变容器颜色。

~~~java
package com.Huang.lesson4;

import javax.swing.*;
import java.awt.*;

public class JFrameDemo {
    public void init() {
        JFrame jFrame = new JFrame("JFrame窗口");
        jFrame.setVisible(true);
        jFrame.setBounds(100,100,200,200);
        //jFrame.setBackground(Color.cyan);这样是无法变色的区别，要把容器读出来
        Container contentPane = jFrame.getContentPane();
        contentPane.setBackground(Color.blue);
        //设置文字JLable
        JLabel label1 = new JLabel("hihihi");
        //让文本居中
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        
        jFrame.add(label1);
        //关闭事件
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JFrameDemo().init();
    }
}
~~~

### 3.2 弹窗

~~~java
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
~~~

### 3.3 标签

Jlabel

~~~java
1. new JLable("xxx");
~~~

图标ICON

~~~java
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
~~~

ImageIcon图片也可以和label关联

~~~java
package com.Huang.lesson4;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageIconDemo extends JFrame {
    public ImageIconDemo() {
        //获取图片地址
        URL url = ImageIconDemo.class.getResource("11.jpeg");
        JLabel label = new JLabel("ImageIcon");
        ImageIcon imageIcon = new ImageIcon(url);
        label.setIcon(imageIcon);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        Container contentPane = getContentPane();
        contentPane.setVisible(true);
        contentPane.add(label);
        setBounds(100,100,200,300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ImageIconDemo();
    }
}
~~~



### 3.4 面板

~~~java
package com.Huang.lesson5;

import javax.swing.*;
import java.awt.*;

public class JpanelDemo extends JFrame {
    public JpanelDemo() {
        Container container = getContentPane();
        container.setLayout(new GridLayout(2,1,10,10));//后两个是面板间距
        JPanel panel1 = new JPanel(new GridLayout(1,3));
        JPanel panel2 = new JPanel(new GridLayout(1,2));
        panel2.add(new JButton("2"));
        panel2.add(new JButton("2"));
        panel1.add(new JButton("1"));
        panel1.add(new JButton("1"));
        panel1.add(new JButton("1"));
        setVisible(true);
        setSize(500,500);
        container.add(panel1);
        container.add(panel2);
    }
    public static void main(String[] args) {
        new JpanelDemo();
    }
}

~~~

#### JScrollPanel

滚动条

<img src="C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210331151911780.png" alt="image-20210331151911780" style="zoom:50%;" />

~~~java
package com.Huang.lesson5;

import javax.swing.*;
import java.awt.*;

public class JscrollDemo extends JFrame {
    public JscrollDemo() {
        Container contentPane = this.getContentPane();

        //文本域
        JTextArea jTextArea = new JTextArea(20, 50);
        jTextArea.setText("nihaonihao");
        //Scroll panel
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        contentPane.add(jScrollPane);
        setVisible(true);
        setBounds(100,100,300,350);

    }

    public static void main(String[] args) {
        new JscrollDemo();

    }
}
~~~

### 3.5 按钮

- 图片按钮

  ~~~java
  package com.Huang.lesson5;
  
  import javax.swing.*;
  import java.awt.*;
  import java.net.URL;
  
  public class JButtonDemo01 extends JFrame {
      public JButtonDemo01() {
          Container container = getContentPane();
          //
          URL resource = JButtonDemo01.class.getResource("2.jfif");
          Icon icon = new ImageIcon(resource);
          JButton jButton = new JButton();
          jButton.setIcon(icon);
          jButton.setToolTipText("图片按钮");
          container.add(jButton);
          setVisible(true);
          setSize(500,300);
  
  
      }
  
      public static void main(String[] args) {
          new JButtonDemo01();
  
      }
  }
  ~~~

- 单选按钮(f分组以后只能选择一个)

  ~~~java
  package com.Huang.lesson5;
  
  import javax.swing.*;
  import java.awt.*;
  import java.net.URL;
  
  public class JButtonDemo02 extends JFrame{
      public JButtonDemo02(){
          Container container = getContentPane();
          URL resource = JButtonDemo01.class.getResource("2.jfif");
          Icon icon = new ImageIcon(resource);
         //单选框
          JRadioButton jRadioButton1 = new JRadioButton("01");
          JRadioButton jRadioButton2 = new JRadioButton("02");
          JRadioButton jRadioButton3 = new JRadioButton("03");
  
          //单选框只能选择一个，分组,每个组织只能选择一个
          ButtonGroup buttonGroup1 = new ButtonGroup();
          buttonGroup1.add(jRadioButton1);
          buttonGroup1.add(jRadioButton2);
          buttonGroup1.add(jRadioButton3);
  
          container.add(jRadioButton1, BorderLayout.CENTER);
          container.add(jRadioButton2, BorderLayout.SOUTH);
          container.add(jRadioButton3, BorderLayout.NORTH);
  
  
  
          setVisible(true);
          setSize(500,300);
      }
  
      public static void main(String[] args) {
          new JButtonDemo02();
      }
  }
  ~~~

- 复选按钮

  ~~~java
  package com.Huang.lesson5;
  
  import javax.swing.*;
  import java.awt.*;
  import java.net.URL;
  
  public class JButtonDemo03 extends JFrame {
      public JButtonDemo03(){
          Container container = getContentPane();
          URL resource = JButtonDemo01.class.getResource("2.jfif");
          Icon icon = new ImageIcon(resource);
          //单选框
          JRadioButton jRadioButton1 = new JRadioButton("01");
          JRadioButton jRadioButton2 = new JRadioButton("02");
          JRadioButton jRadioButton3 = new JRadioButton("03");
  
          //单选框只能选择一个，分组,每个组织只能选择一个
          JCheckBox jCheckBox = new JCheckBox("01");
          JCheckBox jCheckBox2 = new JCheckBox("02");
          container.add(jCheckBox,BorderLayout.NORTH);
          container.add(jCheckBox2,BorderLayout.SOUTH);
          setVisible(true);
          setSize(500,300);
      }
      public static void main(String[] args) {
          new JButtonDemo03();
      }
  }
  ~~~

  <img src="C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210331154430337.png" alt="image-20210331154430337" style="zoom:50%;" />

### 3.6 列表

- 下拉框

  ~~~java
  package com.Huang.lesson6;
  
  import javax.swing.*;
  import java.awt.*;
  
  public class TestComboboxDemo01 extends JFrame {
      public TestComboboxDemo01() {
          Container container = getContentPane();
          JComboBox<Object> status = new JComboBox<>();
          status.addItem("0");
          status.addItem("1");
          status.addItem("2");
          status.addItem("3");
          container.add(status);
          setSize(500,350);
          setVisible(true);
      }
      public static void main(String[] args) {
          new TestComboboxDemo01();
      }
  }
  ~~~

- 列表框

  ~~~java
  package com.Huang.lesson6;
  
  import javax.swing.*;
  import java.awt.*;
  
  public class TestComboboxDemo02 extends JFrame {
      public TestComboboxDemo02() {
          Container container = getContentPane();
          //生成内容内容
          String[] contents = {"1","2","3"};
          JList jList = new JList(contents);
          container.add(jList);
  
          setSize(500,350);
          setVisible(true);
      }
      public static void main(String[] args) {
          new TestComboboxDemo02();
      }
  }
  ~~~

- 应用场景
  - 选择地区，或者一些单个选项
  - 列表，展示信息，一般是动态扩容的

### 3.7 文本框

- TextField

- 密码框

- 文本域

  ~~~java
  package com.Huang.lesson6;
  
  import com.Huang.lesson2.TestText01;
  
  import javax.swing.*;
  import java.awt.*;
  
  public class TestTextDemo01 extends JFrame {
      public TestTextDemo01() {
          Container container = getContentPane();
          //生成内容内容
          JTextField textField = new JTextField("hello");
          JTextField textField2 = new JTextField("word",20);
          JPasswordField jPasswordField = new JPasswordField();
  
          container.add(textField, BorderLayout.SOUTH);
          container.add(textField2, BorderLayout.NORTH);
          container.add(jPasswordField, BorderLayout.CENTER);
          setSize(500,350);
          setVisible(true);
      }
      public static void main(String[] args) {
          new TestTextDemo01();
      }
  }
  //文本域见下拉框，超出范围会自动Scroll
  ~~~

  

## 4 贪吃蛇项目

​	帧，如果时间片足够小，就是动画，一秒30帧，连起来是动画，拆开就是静态的图片！

​    键盘监听

​	定时器Timer

​	主体代码如下

​    优化比如添加账户，云端记录数据库，增在等级，随着蛇长度增加速度，界面优化，多种食物

~~~java
package com.Huang.Snake_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    int length; //蛇其实可以封装
    int[] snakeX = new int[600];//蛇x坐标
    int[] snakeY = new int[500];//蛇y坐标
    String dir;
    boolean isStart;
    boolean isFail = false;
    int foodX;
    int foodY;
    Random random = new Random();
    Timer timer = new Timer(100,this);

    public void init(){
        length = 3;
        snakeX[0] = 100; snakeY[0] = 100;//脑袋坐标
        snakeX[1] = 75; snakeY[1] = 100;//第一个身体坐标
        snakeX[2] = 50; snakeY[2] = 100;//第二个
        dir = "R";
        isStart = false;
        foodX = 25 + 25 * random.nextInt(34);
        foodY = 75 + 25 * random.nextInt(20);

        timer.start();
    }
    public GamePanel(){
        init();
        //获得焦点事件
        this.setFocusable(true);
        addKeyListener(this);
    }

    //绘制面板
    @Override
    protected  void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.WHITE);
        Data.header.paintIcon(this,g,25,10);//头部广告放上去
        g.fillRect(25,75,850,500);//默认游戏界面
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑",Font.BOLD, 18));
        g.drawString("分数："+(length-3),750,40);
        //绘制头方向
        Data.food.paintIcon(this,g,foodX, foodY);
        switch (dir) {
            case "R":
                Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "L":
                Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "U":
                Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "D":
                Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
        }
        //绘制身体
        for(int i = 1; i< length; i++){
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }
        if(!isStart){
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑",Font.BOLD, 40));//设置字体
            g.drawString("按空格键开始游戏",300,300);
        }
        if(isFail){
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑",Font.BOLD, 40));//设置字体
            g.drawString("游戏结束按下空格重新开始",250,250);
            g.drawString("您的分数："+(length-3),250,300);
        }

    }


    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_SPACE:
                if(isFail){
                    isFail = false;
                    init();
                }else{
                    isStart = !isStart;
                    repaint();
                    break;
                }
            case KeyEvent.VK_UP:
                 dir = "U";
                 break;
            case KeyEvent.VK_LEFT:
                 dir = "L";
                 break;
            case KeyEvent.VK_RIGHT:
                 dir = "R";
                 break;
            case KeyEvent.VK_DOWN:
                 dir = "D";
                 break;
        }

    }
    //事件监听
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isStart && !isFail){
            if(snakeX[0] == foodX && snakeY[0] == foodY){
                length++;
                foodX = 25 + 25 * random.nextInt(34);
                foodY = 75 + 25 * random.nextInt(20);
            }
            for(int i = length-1; i>0; i--){
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            if(dir.equals("R")){
                snakeX[0] = snakeX[0]+ 25;
                BoundCheck();
            }
            else if(dir.equals("L")){
                snakeX[0] = snakeX[0] - 25;
                BoundCheck();
            }
            else if(dir.equals("U")){
                snakeY[0] = snakeY[0] - 25;
                BoundCheck();
            }else if(dir.equals("D")){
                snakeY[0] = snakeY[0] + 25;
                BoundCheck();
            }
            for(int i = 1; i< length; i++){
                if(snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0]){
                    isFail = true;
                }
            }
            repaint();

        }



    }
    public void BoundCheck(){
        if(snakeX[0] > 850 && dir.equals("R")){
            snakeX[0] = 25;
        }
        else if(dir.equals("L") && snakeX[0] < 25){
            snakeX[0] = 850;
        }
        else if(dir.equals("U") && snakeY[0] < 75){
            snakeY[0] = 550;
        }
        else if(dir.equals("D") && snakeY[0] > 550){
            snakeY[0] = 75;
        }

    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
~~~

![image-20210331193120134](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210331193120134.png)





