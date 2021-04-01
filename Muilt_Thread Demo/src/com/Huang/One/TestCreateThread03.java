package com.Huang.One;
//use runnable interface to create thread

public class TestCreateThread03 implements Runnable{
    private static boolean flag = false;
    @Override
    public void run() {
            for (int i = 0; i <= 100; i++) {
                if(flag){
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "跑到了" + i);
                if (i == 100) {
                    flag = true;
                }
            }
        }



    public static void main(String[] args) {
        TestCreateThread03 race = new TestCreateThread03();
        Thread thread = new Thread(race,"r");
        Thread thread2 = new Thread(race,"t");
        thread.start();
        thread2.start();



    }
}
