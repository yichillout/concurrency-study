package com.jasper.concurrency.wwj.chapter1;

public class C1_2_DeamonThread01 {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " start");
                Thread.sleep(10_000);
                System.out.println(Thread.currentThread().getName() + " done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread 1");

//        t.setDaemon(true); // if you put the true at here, once the main thread finished, application is not running

        t.start();

        Thread.sleep(5_000);

        // why after this statement, the application is still running?
        // because the main thread and thread 1 are in the same group, JVM detect that active thread is there
        System.out.println(Thread.currentThread().getName() + " done");
    }
}

/**
 * A <-----------------------------------------------> B
 * -------> daemonThread (health check)
 * if the thread create the health check thread is dead and the health check thread is still there,
 * it will make the JVM not terminate the application.
 * if the thread create the health check thread as daemon thread, it will not
 */
