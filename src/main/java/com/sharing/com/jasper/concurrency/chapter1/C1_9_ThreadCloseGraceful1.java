package com.sharing.com.jasper.concurrency.chapter1;

public class C1_9_ThreadCloseGraceful1 {

    private static class Worker extends Thread {
        private volatile boolean start = true;

        @Override
        public void run() {
            // how about there is no chance to get into while(start)? -> solution: C1_11_ThreadCloseGracefulThreadService
            while (start) {
                try {
                    Thread.sleep(500);
                    System.out.println("thread is running...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void shutdown() {
            this.start = false;
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

        worker.shutdown();
        System.out.println("shutdown");
    }
}
