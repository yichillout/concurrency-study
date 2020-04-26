package com.jasper.concurrency.designpattern.chapter8;

public class AsyncFuture<T> implements Future<T> {

    private volatile boolean isDone = false;

    private T result;

    public void done(T result) {
        synchronized (this) {
            this.result = result;
            this.isDone = true;
            this.notifyAll();
        }
    }

    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            while (!isDone) {
                this.wait();
            }
        }
        return result;
    }
}
