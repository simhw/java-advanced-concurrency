package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;

public class RejectMainV4 {
    public static void main(String[] args) {
        // RejectedExecutionHandler 인터페이스를 직접 구현
        ExecutorService es = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.SECONDS, new SynchronousQueue<>(), new MyRejectedExecutionHandler());

        es.execute(new RunnableTask("task1"));
        es.execute(new RunnableTask("task2"));
        es.execute(new RunnableTask("task3"));

        es.close();
    }

    static class MyRejectedExecutionHandler implements RejectedExecutionHandler {

        static AtomicInteger count = new AtomicInteger(0);

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            int i = count.incrementAndGet();
            log("[warning] rejected task count: " + i);
        }
    }
}
