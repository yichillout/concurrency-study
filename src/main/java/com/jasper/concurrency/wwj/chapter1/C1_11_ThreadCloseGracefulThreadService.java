package com.jasper.concurrency.wwj.chapter1;

/**
 * C1_9_ThreadCloseGraceful1 has problem, it will be fixed at this service
 */

public class C1_11_ThreadCloseGracefulThreadService {

    private Thread executeThread;

    private boolean finished = false;

    public void execute(Runnable task) {
        executeThread = new Thread() {
            @Override
            public void run() {
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                runner.start();
                try {
                    runner.join();
                    finished = true;
                    System.out.println("loading is finished");
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
        };
        executeThread.start();
    }

    public void shutdown(long mills) {
        long currentTime = System.currentTimeMillis();
        while (!finished) {
            if ((System.currentTimeMillis() - currentTime) >= mills) {
                System.out.println("time out, need to be terminated");
                executeThread.interrupt();
                break;
            }

            try {
                executeThread.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("execute thread is interrupted.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        C1_11_ThreadCloseGracefulThreadService service = new C1_11_ThreadCloseGracefulThreadService();
        long start = System.currentTimeMillis();
        service.execute(() -> {
            // load a very heavy resource
            try {
                Thread.sleep(15_000); //  if > 10_000, time out, if < 10_000, finished
//                Thread.sleep(1000); //  if > 10_000, time out, if < 10_000, finished
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.shutdown(10_000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
