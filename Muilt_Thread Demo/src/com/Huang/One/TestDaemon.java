package com.Huang.One;

public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        YouY you = new YouY();
        Thread thread1 = new Thread(god);
        thread1.setDaemon(true);
        Thread thread2 = new Thread(you);
        thread1.start();
        thread2.start();
    }
}
class God implements Runnable{
    @Override
    public void run() {
        while(true){
            System.out.println("god bless you");
        }
    }
}
class YouY implements Runnable{
    @Override
    public void run() {
        for(int i =0; i< 36500; i++){
            System.out.println("开心活着");
        }
        System.out.println("goodbye");

    }
}