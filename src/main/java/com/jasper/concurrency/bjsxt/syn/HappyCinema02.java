package com.jasper.concurrency.bjsxt.syn;

import java.util.ArrayList;
import java.util.List;

public class HappyCinema02 {

    public static void main(String[] args) {
        List<Integer> available = new ArrayList<>();
        available.add(1);
        available.add(2);
        available.add(3);
        available.add(4);
        available.add(5);
        available.add(6);
        available.add(7);
        available.add(8);

        List<Integer> seats1 = new ArrayList<>();
        seats1.add(1);
        seats1.add(2);

        List<Integer> seats2 = new ArrayList<>();
        seats2.add(4);
        seats2.add(5);
        seats2.add(6);

        List<Integer> seats3 = new ArrayList<>();
        seats3.add(1);
        seats3.add(2);
        seats3.add(6);

        HappyCinema cinema = new HappyCinema(available, "AMC 18");
        new Thread(new HappyCustomer(cinema, seats1), "Jasper").start();
        new Thread(new HappyCustomer(cinema, seats2), "Mike").start();
        new Thread(new HappyCustomer(cinema, seats3), "Lee").start();
    }
}

class HappyCustomer implements Runnable {

    HappyCinema cinema;

    List<Integer> seats;

    public HappyCustomer(HappyCinema cinema, List<Integer> seats) {
        this.cinema = cinema;
        this.seats = seats;
    }

    @Override
    public void run() {
        synchronized (cinema) {
            boolean flag = cinema.bookTicket(seats);
            if (flag) {
                System.out.println("出票成功：" + Thread.currentThread().getName() + "(" + seats + ") < 位置数量为:" + seats);
            } else {
                System.out.println("出票失败：" + Thread.currentThread().getName() + "(" + seats + ") < 位置不够");
            }
        }

    }
}

class HappyCinema {

    List<Integer> available;
    String name;

    public HappyCinema(List<Integer> available, String name) {
        this.available = available;
        this.name = name;
    }

    public boolean bookTicket(List<Integer> seats) {
        System.out.println("可用位置为" + available);
        List<Integer> copy = new ArrayList<>();
        copy.addAll(available);

        copy.removeAll(seats);

        // 失败
        if (available.size() - copy.size() != seats.size()) {
            return false;
        }

        // 成功
        available = copy;
        return true;
    }
}


