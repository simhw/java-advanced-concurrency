package thread.executor.poolsize;


import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMain1 {
    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);
        ExecutorService es = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS, queue);

        printState(es);

        es.execute(new RunnableTask("task1"));
        printState(es, "task1");

        es.execute(new RunnableTask("task2"));
        printState(es, "task2");

        // queue에서 대기
        es.execute(new RunnableTask("task3"));
        printState(es, "task3");

        es.execute(new RunnableTask("task4"));
        printState(es, "task4");

        // queue가 가득 찬 경우 초과 스레드 생성
        es.execute(new RunnableTask("task5"));
        printState(es, "task5");

        es.execute(new RunnableTask("task6"));
        printState(es, "task6");

        try {
            es.execute(new RunnableTask("task7"));
            printState(es, "task7");

        } catch (RejectedExecutionException e) {
            log("fail to task7");
            log(e.getMessage());

        }

        sleep(3000);
        log("end tasks");
        printState(es);

        sleep(3000);
        log("after maximum pool size alive time");
        printState(es);

        es.close();
        printState(es);

    }
}
