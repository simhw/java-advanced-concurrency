package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            // 스레드 풀에서 관리되는 스레드 수
            int poolSize = poolExecutor.getPoolSize();
            // 작업을 수행하는 스레드 수
            int activeCount = poolExecutor.getActiveCount();
            // 큐에 대기중인 작업의 수
            int queuedTasks = poolExecutor.getQueue().size();
            // 완료된 작업의 수
            long completedTaskCount = poolExecutor.getCompletedTaskCount();

            log("[poolSize: " + poolSize + ", activeCount: " + activeCount + ", queuedTasks: "
                    + queuedTasks + ", completedTaskCount: " + completedTaskCount + "]");
        } else {
            log(executorService);
        }
    }

    public static void printState(ExecutorService executorService, String taskName) {
        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            int poolSize = poolExecutor.getPoolSize();
            int activeCount = poolExecutor.getActiveCount();
            int queuedTasks = poolExecutor.getQueue().size();
            long completedTaskCount = poolExecutor.getCompletedTaskCount();

            log(taskName + " → [poolSize: " + poolSize + ", activeCount: " + activeCount + ", queuedTasks: "
                    + queuedTasks + ", completedTaskCount: " + completedTaskCount + "]");
        } else {
            log(executorService);
        }
    }
}
