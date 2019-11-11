package com.sharing.com.jasper.concurrency.chapter2;

public class C2_1_SynchronizedTest1 {

    public static void main(String[] args) {
        final TicketWindowRunnable ticketWindow = new TicketWindowRunnable();
        Thread windowThread1 = new Thread(ticketWindow, "window 1");
        Thread windowThread2 = new Thread(ticketWindow, "window 2");
        Thread windowThread3 = new Thread(ticketWindow, "window 3");
        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
    }
}
