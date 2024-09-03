package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ExecutorBasicMain {
    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(2, 2, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        log("============ init ============");
        printState(es);
        es.execute(new RunnableTask("task1"));
        es.execute(new RunnableTask("task2"));
        es.execute(new RunnableTask("task3"));
        es.execute(new RunnableTask("task4"));

        log("============ tasking ============");
        printState(es);

        sleep(3000);
        log("============ end ============ ");
        printState(es);

        es.close();
        log("============ shutdown ============");
        printState(es);
    }

}
