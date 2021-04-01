package com.Huang.One;

public class TestStop implements Runnable{
    //建议线程正常停止 利用次数不建议死循环
    boolean flag = false;//设置一个标志位
    int i = 0;
    @Override
    public void run() {
        while(!flag){
            System.out.println("xianzaishi"+(i++));
        }
    }
    public void stop(){
        flag = true;
    }


    public static void main(String[] args) {
        TestStop stop = new TestStop();
        Thread thread = new Thread(stop);
        thread.start();
        for(int i = 0; i < 1000; i++){
            System.out.println("main"+i);
            if(i == 900){
                stop.stop();
            }
        }


    }
}
