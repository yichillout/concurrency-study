package com.jasper.interview;

public class Synchronized_ABC {
    public static class ThreadPrinter implements Runnable {
        private String name;
        private LockObject prev;
        private LockObject self;

        private ThreadPrinter(String name, LockObject prev, LockObject self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while (count > 0) { // 多线程并发，不能用if，必须使用while循环
                synchronized (prev) {  // 先获取 prev 锁
                    System.out.println(this.name + " get lock prev : " + prev.id);
                    synchronized (self) {// 再获取 self 锁
                        System.out.println(this.name + " get lock self : " + self.id);
                        System.out.println(name);// 打印
                        count--;

                        self.notifyAll();// 唤醒其他线程竞争self锁，注意此时self锁并未立即释放。
                    }
                    // 此时执行完self的同步块，这时self锁才释放。
                    try {
                        if (count == 0) {// 如果count==0,表示这是最后一次打印操作，通过notifyAll操作释放对象锁。
//                            prev.notifyAll();
                        } else {
                            prev.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        LockObject a = new LockObject(1);
        LockObject b = new LockObject(2);
        LockObject c = new LockObject(3);
        ThreadPrinter pa = new ThreadPrinter("A", c, a);
        ThreadPrinter pb = new ThreadPrinter("B", a, b);
        ThreadPrinter pc = new ThreadPrinter("C", b, c);

        new Thread(pa).start();
        Thread.sleep(5);// 保证初始ABC的启动顺序
        new Thread(pb).start();
        Thread.sleep(5);
        new Thread(pc).start();
        Thread.sleep(5);
    }
}

class LockObject {

    int id;

    public LockObject(int id) {
        this.id = id;
    }
}