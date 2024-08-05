package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV2 {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();

        sleep(4000);

        // sleep() 메서드를 호출하거나 호출 중일 때 발생
        thread.interrupt();
        log("work interrupted state1 = " + thread.isInterrupted());
        log("stop work");
    }

    static class MyTask implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    log("work");
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                log("work interrupted state2 = " + Thread.currentThread().isInterrupted());
                log("interrupted message = " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
