package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV3 {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();

        sleep(1000);

        thread.interrupt();
        log("work interrupted state1 = " + thread.isInterrupted());
        log("stop work");
    }

    static class MyTask implements Runnable {
        @Override
        public void run() {
            // 인터럽트 상태를 유지하므로 비정상 종료 가능성이 있음
            while (!Thread.currentThread().isInterrupted()) {
                log("work");
            }
            log("work interrupted state2 = " + Thread.currentThread().isInterrupted());

            try {
                log("sleep() start");
                Thread.sleep(1000);
                log("sleep() end");

            } catch (InterruptedException e) {
                log("work interrupted state3 = " + Thread.currentThread().isInterrupted());
                log("interrupted message = " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
