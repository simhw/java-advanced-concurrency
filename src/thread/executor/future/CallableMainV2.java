package thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class CallableMainV2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        log("submit()");
        Future<Integer> future = es.submit(new MyCallable());
        // 요청 스레드는 대기하지 않음
        log("future = " + future);

        log("main thread waiting");
        int result = future.get();
        log("main thread runnable");

        log("result = " + result);
        log("future = " + future);

        es.close();
        log("close()");
    }

    public static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() {
            log("start()");

            sleep(1000);
            int value = new Random().nextInt(100);

            log("value = " + value);
            log("end()");

            return value;
        }
    }
}
