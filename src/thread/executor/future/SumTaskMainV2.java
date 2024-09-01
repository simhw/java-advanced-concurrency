package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;

public class SumTaskMainV2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        ExecutorService es = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = es.submit(task1);
        Future<Integer> future2 = es.submit(task2);

        Integer sum1 = future1.get();
        Integer sum2 = future2.get();

        log("sum1 = " + sum1);
        log("sum2 = " + sum2);

        int result = sum1 + sum2;
        log("result = " + result);

        es.close();
    }

    static class SumTask implements Callable<Integer> {
        int start;
        int end;

        public SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Integer call() throws InterruptedException {
            log("callable start()");
            int sum = 0;
            Thread.sleep(1000);

            for (int i = start; i <= end; i++) {
                sum += i;
            }

            log("callable end()");
            return sum;
        }
    }
}
