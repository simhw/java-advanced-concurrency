package thread.sync.test;

import static util.MyLogger.log;

public class SyncTest2Main {

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                counter.count();
            }
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");

        thread1.start();
        thread2.start();
    }

    static class Counter {
        public void count() {
            // 지역 변수는 스레드의 개별 공간인 스택 영역에 생성
            int localValue = 0;

            for (int i = 0; i < 1000; i++) {
                localValue = localValue + 1;
            }

            log("결과: " + localValue);
        }
    }
}
