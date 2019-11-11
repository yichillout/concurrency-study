package com.sharing.com.jasper.concurrency.chapter1;

public class C1_3_DeamonThread02 {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {

            Thread healthCheckThread = new Thread(() -> {
                try {
                    while (true) {
                        System.out.println("health check...");
                        Thread.sleep(1_000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            healthCheckThread.setDaemon(true);
            healthCheckThread.start();

            try {
                Thread.sleep(1_000);
                System.out.println("t thread is done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.start();
    }
}