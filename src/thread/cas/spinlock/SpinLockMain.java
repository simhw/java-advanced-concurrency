package thread.cas.spinlock;

import static util.MyLogger.log;

public class SpinLockMain {

    public static void main(String[] args) {

        SpinLock lock = new SpinLock();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    log("start to business logic");
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
    }
}
