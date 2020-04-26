package com.jasper.concurrency.bjsxt.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁 ReentrantLock：说明这个锁可以延续使用
 */
public class LockTest04 {

    ReentrantLock lock = new ReentrantLock();

    public void doSomething01() throws InterruptedException {
        lock.lock();
        System.out.println("doSomething01 method is called - count:" + lock.getHoldCount());
        doSomething02();
        lock.unlock();
        System.out.println("doSomething01 nulock - count:" + lock.getHoldCount());
    }

    // 可重入
    public void doSomething02() throws InterruptedException {
        lock.lock();
        System.out.println("doSomething02 method is called - count:" + lock.getHoldCount());
        lock.unlock();
        System.out.println("doSomething02 nulock - count:" + lock.getHoldCount());
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest04 test = new LockTest04();
        test.doSomething01();
        test.doSomething02();
    }
}
