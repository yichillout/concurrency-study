package com.bjsxt.syn;

public class TrainTicket01 {

    public static void main(String[] args) {
        WebTrainTicket wtt = new WebTrainTicket(4, "Train Tickets");
        new Passenger(wtt, "Jasper", 2).start();
        new Passenger(wtt, "Mike", 1).start();
    }
}

class Passenger extends Thread {

    // 变量放在Thread的子类这里
    int seats;

    public Passenger(Runnable target, String name, int seats) {
        super(target, name);
        this.seats = seats;
    }

}

class WebTrainTicket implements Runnable {

    int available;
    String name;

    public WebTrainTicket(int available, String name) {
        this.available = available;
        this.name = name;
    }

    @Override
    public void run() {
        // 当前线程转成子类(这里是重点)
        Passenger p = (Passenger) Thread.currentThread();
        boolean flag = this.bookTicket(p.seats);
        if (flag) {
            System.out.println("出票成功：" + Thread.currentThread().getName() + " < 位置数量为:" + p.seats);
        } else {
            System.out.println("出票失败：" + Thread.currentThread().getName() + " < 位置不够");
        }
    }

    public synchronized boolean bookTicket(int seats) {
        System.out.println("可用位置为" + available);
        if (seats > available) {
            return false;
        }
        available -= seats;
        return true;
    }
}

