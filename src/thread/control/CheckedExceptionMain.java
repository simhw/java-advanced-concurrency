package thread.control;

import thread.util.ThreadUtils;

public class CheckedExceptionMain {
    public static void main(String[] args) {
        Thread thread = new Thread(new CheckedRunnable(), "CheckedRunnable");
        thread.start();
    }

    static class CheckedRunnable implements Runnable {
        @Override
        public void run() {
            // throw new Exception();
            ThreadUtils.sleep(1000);
        }
    }
}
