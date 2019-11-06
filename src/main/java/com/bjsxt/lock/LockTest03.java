package com.bjsxt.lock;

/**
 * 可重入锁：说明这个锁可以延续使用
 */
public class LockTest03 {

    ReLock lock = new ReLock();

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
        LockTest03 test = new LockTest03();
        test.doSomething01();
        test.doSomething02();
    }
}

class ReLock {
    // 是否占用
    private boolean isLocked = false;
    Thread lockedBy = null;
    private int holdCount = 0;

    // 使用锁
    public synchronized void lock() throws InterruptedException {
        Thread t = Thread.currentThread();
        while (isLocked && lockedBy != t) {
            wait();
        }
        isLocked = true;
        lockedBy = t;
        holdCount++;
    }

    // 释放锁
    public synchronized void unlock() {
        if (Thread.currentThread() == lockedBy) {
            holdCount--;
            if (holdCount == 0) {
                isLocked = false;
                notify();
                lockedBy = null;
            }
        }
    }

    public int getHoldCount() {
        return holdCount;
    }
}
