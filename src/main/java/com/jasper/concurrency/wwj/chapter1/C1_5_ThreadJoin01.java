package com.jasper.concurrency.wwj.chapter1;

import java.util.stream.IntStream;

public class C1_5_ThreadJoin01 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            IntStream.range(1, 100)
                    .forEach(i -> {
                        System.out.println(Thread.currentThread().getName() + "->" + i);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
        });

        Thread t2 = new Thread(() -> {
            IntStream.range(1, 100)
                    .forEach(i -> {
                        System.out.println(Thread.currentThread().getName() + "->" + i);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("t1 and t2 done");

        IntStream.range(1, 100)
                .forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
    }
}
