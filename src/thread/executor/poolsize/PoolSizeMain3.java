package thread.executor.poolsize;


import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static thread.executor.ExecutorUtils.printState;

public class PoolSizeMain3 {
    public static void main(String[] args) {
        // 스레드 무한 확장
        ExecutorService es = Executors.newCachedThreadPool();
        //  ThreadPoolExecutor es = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
        printState(es);

        for (int i = 1; i <= 7; i++) {
            es.execute(new RunnableTask("task" + i));
            printState(es, "task" + i);
        }

        es.close();
        printState(es);
    }
}
