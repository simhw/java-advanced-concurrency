package thread.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;

public class CasMainV3 {

    private static int THREAD_COUNT = 10;

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        Runnable task = new Runnable() {
            public void run() {
                incrementAndGet(atomicInteger);
            }
        };

        List<Thread> threads = new ArrayList<>(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("result value = " + atomicInteger.get());
    }

    public static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;

        // 다른 스레드가 값을 먼저 증가해 문제가 발생한 경우 재시도
        do {
            getValue = atomicInteger.get();
            log("getValue: " + getValue);

            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result: " + result);

        } while (!result);

        return getValue + 1;
    }
}
