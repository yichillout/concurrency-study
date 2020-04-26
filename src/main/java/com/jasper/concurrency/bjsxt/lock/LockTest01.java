package com.jasper.concurrency.bjsxt.lock;

public class LockTest01 {

    public void test() {

        // 第一次获得锁
        synchronized (this) {
            while (true) {
                // 第二次获得锁
                synchronized (this) {
                    System.out.println("ReentrantLock");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public static void main(String[] args) {
        new LockTest01().test();
    }
}
