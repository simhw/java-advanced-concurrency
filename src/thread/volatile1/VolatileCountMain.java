package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileCountMain {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");

        log("task.ready = " + task.ready);
        log("start() start");
        thread.start();

        sleep(3000);
        task.ready = false;

        log("ready = " + task.ready + ", count = " + task.count + " in main");
    }

    static class MyTask implements Runnable {
//         boolean ready = true;
//         long count;

        volatile boolean ready = true;
        volatile long count;

        @Override
        public void run() {
            log("run() start");
            while (ready) {
                count++;
                if (count % 100_000_000 == 0) {
                    log("ready = " + ready + ", count = " + count + " in while");
                }
            }
            log("ready = " + ready + ", count = " + count + " in task");
            log("run() end");
        }
    }
}
