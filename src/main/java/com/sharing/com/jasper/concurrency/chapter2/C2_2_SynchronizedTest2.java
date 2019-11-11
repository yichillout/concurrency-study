package com.sharing.com.jasper.concurrency.chapter2;

// run this and open the jconsole
public class C2_2_SynchronizedTest2 {

    private final static Object LOCK = new Object();

    public static void main(String[] args) {

        Runnable runnable = () -> {
            try {
                synchronized (LOCK) {
                    Thread.sleep(100_000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();
    }
}
