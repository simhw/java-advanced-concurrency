package thread.control.volatile1;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class VolatileFlagMain {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");

        log("task.ready = " + task.ready);
        log("start() start");
        thread.start();

        sleep(1000);
        // 캐시 메모리 내 변수 값 변경
        task.ready = false;

        log("task.ready = " + task.ready);
        log("start() end");
    }

    static class MyTask implements Runnable {
        // boolean ready = true;

        // 메인 메모리로 즉시 쓰여지는 값
        volatile boolean ready = false;

        @Override
        public void run() {
            log("run() start");
            while (ready) {
            }
            log("run() end");
        }
    }
}
