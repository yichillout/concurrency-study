package com.jasper.concurrency.mmall.example.producerconsumer;


class Producer extends Thread {

    SynContainer container;

    public Producer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("produce --> " + i + " item");
            container.push(new Item(i));
        }
    }
}

class Consumer extends Thread {

    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("consume --> " + container.pop().id + " item");
        }

    }
}

class SynContainer {

    Item[] items = new Item[10]; // container
    int count = 0;

    public synchronized void push(Item item) {
        if (count == items.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        items[count] = item;
        count++;
        this.notifyAll();
    }

    public synchronized Item pop() {
        if (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        Item item = items[count];
        this.notifyAll();
        return item;
    }

}

class Item {

    int id;

    public Item(int id) {
        this.id = id;
    }
}

public class ProducerAndConsumerWithContainer {
    public static void main(String[] args) throws InterruptedException {
        SynContainer container = new SynContainer();
        new Producer(container).start();
        new Consumer(container).start();
    }
}
