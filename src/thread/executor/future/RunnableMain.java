package thread.executor.future;

import java.util.Random;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class RunnableMain {

    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task);

        thread.start();
        thread.join();
        int result = task.value;

        log("result = " + result);
    }

    public static class MyRunnable implements Runnable {

        public int value;

        @Override
        public void run() {
            log("start()");

            sleep(1000);
            value = new Random().nextInt(100);

            log("value = " + value);
            log("end()");
        }
    }
}
