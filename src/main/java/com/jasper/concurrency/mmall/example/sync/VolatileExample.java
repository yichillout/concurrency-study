package com.jasper.concurrency.mmall.example.sync;

public class VolatileExample {

//    private static volatile num =0;
    private static int num = 0; //此处没有volatile的话，会进入死循环

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (num == 0) {
                //此处不编写代码，让系统没法有时间去更新num
            }
        }).start();

        Thread.sleep(1000);
        System.out.println("finished");

    }
}
