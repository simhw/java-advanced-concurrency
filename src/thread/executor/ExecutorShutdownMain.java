package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class ExecutorShutdownMain {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);

        es.execute(new RunnableTask("task1", 1000));
        es.execute(new RunnableTask("task1", 1000));
        es.execute(new RunnableTask("task1", 1000));
        // 100초 대기
        es.execute(new RunnableTask("task1", 100_000));

        printState(es);
        log("우아한 종료 시작");
        shutdownAndAwaitTermination(es);
        log("우아한 종료 끝");
        printState(es);
    }

    public static void shutdownAndAwaitTermination(ExecutorService es) {
        // non-blocking
        // 새로운 작업을 받지 않음
        // 처리 중이거나, 큐에 이미 대기중인 작업은 처리
        // 이후 풀의 스레드 종료
        log("shutdown()");
        es.shutdown();

        try {
            if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
                log("failed to shut down executor(1)");
                es.shutdownNow();

                // interrupt 이후 자원을 정리하는 작업 시간 대기
                if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
                    log("failed to shut down executor(2)");
                }
            }
        } catch (InterruptedException e) {
            es.shutdownNow();
        }
    }
}

