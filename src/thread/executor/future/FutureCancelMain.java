package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class FutureCancelMain {

    private static boolean mayInterruptIfRunning = false; // true/false

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<String> future = es.submit(new MyTask());
        log("future.state = " + future.state());

        sleep(3000);
        log("future.cancel(" + mayInterruptIfRunning + ")");
        // future를 취소 상태로 변경
        // true인 경우 Thread.interrupt() 호출
        // false인 경우 이미 실행 중인 작업을 중단하지 않음
        boolean cancelResult = future.cancel(mayInterruptIfRunning);

        log("future.state = " + future.state());
        log("future.cancel(" + mayInterruptIfRunning + "), result: " + cancelResult);

        try {
            log("future.result: " + future.get());
        } catch (CancellationException e) {
            log("future already cancelled.");
        } catch (InterruptedException | ExecutionException e) {
            e.fillInStackTrace();
        }

        es.close();
    }

    static class MyTask implements Callable<String> {
        @Override
        public String call() {
            try {
                for (int i = 0; i < 10; i++) {
                    log("working: " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                log(e.getMessage());
                return "fail";
            }
            return "success";
        }
    }
}
