package com.jasper.concurrency.designpattern.chapter8;

/**
 * Future
 * FutureTask
 * FutureService
 */
public class AsyncInvoker1 {

    public static void main(String[] args) throws InterruptedException {

        FutureService futureService = new FutureService();

        Future<String> future = futureService.submit(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Finished";
        });

        System.out.println("===========================");
        System.out.println("do other thing......");
        Thread.sleep(1000);
        System.out.println("===========================");
        System.out.println(future.get());
    }
}
