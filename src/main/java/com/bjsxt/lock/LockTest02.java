package com.bjsxt.lock;

/**
 * 不可重入锁：说明这个锁不可以延续使用
 */
public class LockTest02 {

    Lock lock = new Lock();

    public void doSomething01() throws InterruptedException {
        lock.lock();
        System.out.println("doSomething01 method is called");
        doSomething02();
        lock.unlock();
    }

    // 不可重入
    public void doSomething02() throws InterruptedException {
        lock.lock();
        System.out.println("doSomething02 method is called");
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest02 test = new LockTest02();
        test.doSomething01(); // 这里会进入死循环
    }
}

class Lock {
    // 是否占用
    private boolean isLocked = false;

    // 使用锁
    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }

        isLocked = true;
    }

    // 释放锁
    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}
