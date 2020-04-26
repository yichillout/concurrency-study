package com.jasper.concurrency.wwj.chapter1;

public class C1_1_CreateThread {

    private static int counter = 1;

    public static void main(String[] args) {

        Thread t1 = new Thread(null, new Runnable() {
            @Override
            public void run() {
                try {
                    add(1);
                } catch (Error e) {
                    System.out.println(counter);
                }
            }

            private void add(int i) {
                counter++;
                add(i + 1);
            }
        }, "Test", 1 << 24); // at here to test the stackSize parameter
        t1.start();

//        Thread t2 = new Thread(null, new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    add(1);
//                } catch (Error e) {
//                    System.out.println(counter);
//                }
//            }
//
//            private void add(int i) {
//                counter++;
//                add(i + 1);
//            }
//        }, "Test2"); // at here is the default size
//        t2.start();
    }
}
