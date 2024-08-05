package thread.start;

import static util.MyLogger.log;
// 내부 클래스
public class InnerRunnableMainV1 {
    public static void main(String[] args) {
        log("main() start");

        MyRunnable runnable = new MyRunnable();
        new Thread(runnable).start();

        log("main() end");
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            log("run() start");
        }
    }
}
