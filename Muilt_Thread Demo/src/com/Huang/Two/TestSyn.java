package com.Huang.Two;

public class TestSyn {
    public static void main(String[] args) {
        BuyTicket b1 = new BuyTicket();
        Thread t1 = new Thread(b1,"小明");
        Thread t2 = new Thread(b1,"小红");
        Thread t3 = new Thread(b1,"小花");
        t1.start();
        t2.start();
        t3.start();



    }
}
class BuyTicket implements Runnable{
    private int number = 10;
    boolean flag = true;
    @Override
    public void run() {
        while(flag){
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void buy() throws InterruptedException {
        if(number > 0){
            Thread.sleep(1);
            System.out.println(Thread.currentThread().getName()+"买到了"+number);
            number--;
        }
        else{
            flag = false;
        }

    }
}
