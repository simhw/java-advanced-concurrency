package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class FutureExceptionMain {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);

        log("submit()");
        Future<Integer> future = es.submit(new ExCallable());
        sleep(1000);

        try {
            log("future.state()" + future.state());
            Integer result = future.get();
            log("result = " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        es.close();
        log("close()");

    }

    static class ExCallable implements Callable<Integer> {
        @Override
        public Integer call() {
            log("IllegalStateException");
            throw new IllegalStateException("cause exception in callable");
        }
    }
}
