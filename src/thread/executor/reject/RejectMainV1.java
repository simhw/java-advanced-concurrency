package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static util.MyLogger.log;

public class RejectMainV1 {
    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy());

        // 작업이 거절된 경우 RejectedExecutionException 발생
        es.execute(new RunnableTask("task1"));

        try {
            es.execute(new RunnableTask("task2"));
        } catch (RejectedExecutionException e) {
            log(e);
        }

        es.close();
    }
}
