package com.mmall.concurrency.example.singleton;

public class SingletonExample10 {


    private SingletonExample10() {

    }

    public static SingletonExample10 getSingletonExample10() {
        return Singleton.SINGLETON.getSingleton();
    }


    private enum Singleton {

        SINGLETON;

        SingletonExample10 singletonExample10;

        Singleton() {
            singletonExample10 = new SingletonExample10();
        }

        public  SingletonExample10 getSingleton() {
            return singletonExample10;
        }
    }

}
