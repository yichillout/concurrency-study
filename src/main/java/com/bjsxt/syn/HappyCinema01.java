package com.bjsxt.syn;

public class HappyCinema01 {

    public static void main(String[] args) {
        Cinema cinema = new Cinema(20, "AMC 18");
        new Thread(new Customer(cinema, 2), "Jasper").start();
        new Thread(new Customer(cinema, 1), "Mike").start();
        new Thread(new Customer(cinema, 19), "Lee").start();
    }
}

class Customer implements Runnable {

    Cinema cinema;

    int seats;

    public Customer(Cinema cinema, int seats) {
        this.cinema = cinema;
        this.seats = seats;
    }

    @Override
    public void run() {
        synchronized (cinema) {
            boolean flag = cinema.bookTicket(seats);
            if (flag) {
                System.out.println("出票成功：" + Thread.currentThread().getName() + " < 位置数量为:" + seats);
            } else {
                System.out.println("出票失败：" + Thread.currentThread().getName() + " < 位置不够");
            }
        }

    }
}

class Cinema {

    int available;
    String name;

    public Cinema(int available, String name) {
        this.available = available;
        this.name = name;
    }

    public boolean bookTicket(int seats) {
        System.out.println("可用位置为" + available);
        if (seats > available) {
            return false;
        }
        available -= seats;
        return true;
    }
}


