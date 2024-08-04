package thread.control.interrupt;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class ThreadStopMainV4 {
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
            // 인터럽트 상태인 경우 정상 상태로 다시 변경
            while (!Thread.interrupted()) {
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
