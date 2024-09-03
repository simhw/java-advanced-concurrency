package thread.executor.poolsize;


import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;

public class PoolSizeMain2 {
    public static void main(String[] args) {
        // 대기 큐 무한 확장
        ExecutorService es = Executors.newFixedThreadPool(2);
        // ExecutorService es = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        printState(es);

        for (int i = 1; i <= 7; i++) {
            es.execute(new RunnableTask("task" + i));
            printState(es, "task" + i);
        }

        es.close();
        printState(es);
    }
}
