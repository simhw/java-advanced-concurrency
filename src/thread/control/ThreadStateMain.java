package thread.control;

import static thread.util.MyLogger.log;

public class ThreadStateMain {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnable(), "myThread");
        log("myThread.state1 = " + thread.getState());
        thread.start();

        Thread.sleep(1000);
        log("myThread.state3 = " + thread.getState());

        Thread.sleep(3000);
        log("myThread.state5 = " + thread.getState());
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                log("run() start");
                log("myThread.state2 = " + Thread.currentThread().getState());

                log("sleep() start");
                Thread.sleep(3000);
                log("sleep() end");

                log("run() end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
