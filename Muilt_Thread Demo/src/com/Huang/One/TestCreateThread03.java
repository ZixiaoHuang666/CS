package com.Huang.One;
//use runnable interface to create thread
public class TestCreateThread03 implements Runnable{
    @Override
    public void run() {
        for(int i =0; i< 2000; i++){
            System.out.println("线程"+i);
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new TestCreateThread03());
        thread.start();
        for(int i =0; i< 2000; i++){
            System.out.println("Main"+i);
        }
        System.out.println("hsssss");
        System.out.println("");

    }
}
