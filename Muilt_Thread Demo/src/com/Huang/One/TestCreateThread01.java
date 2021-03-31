package com.Huang.One;
//
public class TestCreateThread01 extends Thread{
    @Override
    public void run() {
        for(int i = 0; i< 1000; i++)
            System.out.println("Thread"+i);
    }

    public static void main(String[] args) {
        TestCreateThread01 n1 = new TestCreateThread01();
        n1.start();
        for(int i = 0; i< 1000; i++){
            System.out.println("Main"+i);
        }

    }
}
