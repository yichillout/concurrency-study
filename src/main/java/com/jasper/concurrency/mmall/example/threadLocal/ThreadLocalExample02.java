package com.jasper.concurrency.mmall.example.threadLocal;


/**
 * 1.InheritableThreadLocal的学习
 */
public class ThreadLocalExample02 {

    private static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set(10);
        System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());

        //继承上下文环境的数据，拷贝一份给子线程 起点
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
            threadLocal.set(200);
            System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
        }).start();
    }
}
