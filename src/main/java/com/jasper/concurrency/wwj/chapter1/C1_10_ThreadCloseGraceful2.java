package com.jasper.concurrency.wwj.chapter1;

public class C1_10_ThreadCloseGraceful2 {

    private static class Worker extends Thread {


        @Override
        public void run() {
//            while (true) {
//                try {
//                    Thread.sleep(500);
//                    System.out.println("thread is running...");
//                } catch (InterruptedException e) {
//                    break;
//                }
//            }

            while (true) {
                if (Thread.interrupted()) {
                    break;
                }

                try {
                    Thread.sleep(500);
                    System.out.println("thread is running...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

//            while (!Thread.currentThread().interrupted()) {
//                try {
//                    Thread.sleep(500);
//                    System.out.println("thread is running...");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.interrupt();
        System.out.println("interrupt");
    }
}
