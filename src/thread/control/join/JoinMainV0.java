package thread.control.join;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.*;

public class JoinMainV0 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Job(), "thread-1");
        thread1.start();

        Thread thread2 = new Thread(new Job(), "thread-2");
        thread2.start();
    }

    static class Job implements Runnable {
        @Override
        public void run() {
            log("run() start");
            sleep(2000);
            log("run() end");
        }
    }
}
