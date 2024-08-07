package thread.sync.test;

import static util.MyLogger.log;

public class SyncTest1Main {

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        Runnable task = new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.increment();
                }
            }
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        log("결과: " + counter.getCount());
    }

    static class Counter {
        private int count = 0;

        public synchronized void increment() {
            // 1. read count
            // 2. add 1
            // 3. restore count
            count = count + 1;
        }

        public int getCount() {
            return count;
        }
    }
}
