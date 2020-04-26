package com.jasper.concurrency.wwj.chapter1;

public class C1_8_ThreadInterrupt {

    private static Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {

        // example 1
//        Thread t = new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    System.out.println(">>>" + isInterrupted());
//                }
//            }
//        };

        // example 2
//        Thread t = new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        System.out.println("sleep is interrupted");
//                        e.printStackTrace();
//                        break;
//                    }
//                }
//            }
//        };

        // example 3
//        Thread t = new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    synchronized (MONITOR) {
//                        try {
//                            MONITOR.wait(100);
//                            System.out.println(">>>" + isInterrupted());
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                            break;
//                        }
//                    }
//                }
//            }
//        };

        // example 4
//        Thread t = new Thread(() -> {
//            while (true) {
//                synchronized (MONITOR) {
//                    try {
//                        MONITOR.wait(100);
//                        System.out.println(">>>" + Thread.interrupted());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                        break;
//                    }
//                }
//            }
//        });

        // example 1,2,3,4
//        t.start();
//        Thread.sleep(5000);
//        System.out.println(t.isInterrupted());
//        t.interrupt();
//        System.out.println(t.isInterrupted());


        // example 5
//        Thread t1 = new Thread("mythread1") {
//
//            @Override
//            public void run() {
//                while (true) {
//
//                }
//            }
//        };
//        t1.start();
//
//        Thread main = Thread.currentThread();
//
//        Thread t2 = new Thread("mythread2") {
//
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(this.getName() + "->" + this.isInterrupted());
//                System.out.println(main.getName());
//                main.interrupt();
//            }
//        };
//        t2.start();
//
//        try {
//            t1.join();
//            System.out.println("-----------");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
