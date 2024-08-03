package thread.control.join;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class JoinMainV4 {
    public static void main(String[] args) throws InterruptedException {
        SumTask task1 = new SumTask(1, 50);
        Thread thread1 = new Thread(task1, "thread-1");

        thread1.start();

        // 지정 시간 까지만 대기
        // TIMED_WAITING
        thread1.join(1000);

        log("task1.result = " + task1.result);
        int result = task1.result;
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
