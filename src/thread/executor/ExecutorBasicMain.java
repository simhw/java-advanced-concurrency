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
        ExecutorService es = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

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

    static class RunnableTask implements Runnable {

        private final String name;
        private int sleepMs = 1000;

        RunnableTask(String name) {
            this.name = name;
        }

        RunnableTask(String name, int sleepMs) {
            this.name = name;
            this.sleepMs = sleepMs;
        }

        @Override
        public void run() {
            log(name + " start()");
            sleep(sleepMs);
            log(name + " end()");
        }
    }
}
