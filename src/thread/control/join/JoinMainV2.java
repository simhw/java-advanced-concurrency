package thread.control.join;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class JoinMainV2 {
    public static void main(String[] args) throws InterruptedException {
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        sleep(3000);

        log("task1.result = " + task1.result);
        log("task2.result = " + task2.result);

        int result = task1.result + task2.result;
        log("result = " + result);
    }

    static class SumTask implements Runnable {
        int start;
        int end;
        int result;

        SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            int sum = 0;
            sleep(2000);

            for (int i = start; i <= end; i++) {
                sum += i;
            }
            result = sum;
        }
    }
}
