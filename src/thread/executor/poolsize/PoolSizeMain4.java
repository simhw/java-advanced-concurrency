package thread.executor.poolsize;


import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class PoolSizeMain4 {

    static final int NORMAL = 1100;
    // 12:27:47.671 [     main] time: 11203

    static final int EMERGENCY = 1200;
    // 12:28:19.726 [     main] time: 6228

    static final int REJECT = 1201;
    // task1201 → Task thread.executor.RunnableTask@527740a2 rejected
    // 12:29:05.867 [     main] time: 6311

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(100, 200, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000));

        long start = System.currentTimeMillis();

        // 최대 1000 + 200
        for (int i = 1; i <= REJECT; i++) {
            String task = "task" + i;
            try {
                es.execute(new RunnableTask(task));
                printState(es, task);

            } catch (RejectedExecutionException e) {
                log(task + " → " + e.getMessage());
            }
        }

        es.close();
        long end = System.currentTimeMillis();
        log("time: " + (end - start));
    }
}
