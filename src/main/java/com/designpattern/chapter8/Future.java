package com.designpattern.chapter8;

public interface Future<T> {

    T get() throws InterruptedException;
}
