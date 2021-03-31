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
