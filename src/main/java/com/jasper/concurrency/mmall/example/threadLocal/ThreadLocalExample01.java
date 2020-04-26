package com.jasper.concurrency.mmall.example.threadLocal;

import java.util.Random;

/**
 * 1.构造器：哪里调用，上下文就属于哪里，找线程体
 */
public class ThreadLocalExample01 {

    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 666);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Job()).start();
        }

    }

    static class Job implements Runnable {

        public Job() {
            // 这里属于主线程的范围
            threadLocal.set(100);
            System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
        }

        @Override
        public void run() {
            Random rand = new Random();
            int change = rand.nextInt((50 - 1) + 1) + 1;
            threadLocal.set(threadLocal.get() - change);
            System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get() + "(" + change + ")");
        }
    }

}
