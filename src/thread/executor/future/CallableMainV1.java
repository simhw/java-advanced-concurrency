package thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class CallableMainV1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Integer> future = es.submit(new MyCallable());

        int result = future.get();
        log("result = " + result);

        es.close();
    }

    // 결과를 필드에 담아두는 것이 아니라, 결과를 반환
    public static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            log("start()");

            sleep(1000);
            int value = new Random().nextInt(100);

            log("value = " + value);
            log("end()");

            return value;
        }
    }
}
