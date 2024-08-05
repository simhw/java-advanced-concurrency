package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV1 {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();

        sleep(4000);

        // work 스레드가 WAIT 상태로 즉각적으로 TERMINATE 불가능
        log("stop work");
        myTask.flag = false;
    }

    static class MyTask implements Runnable {

        volatile boolean flag = true;

        @Override
        public void run() {
            while (flag) {
                log("work");
                sleep(3000);
            }
        }
    }
}
