package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectMainV3 {
    public static void main(String[] args) {
        // 호출 스레드가 직접 작업을 수행
        ExecutorService es = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());

        es.execute(new RunnableTask("task1"));
        es.execute(new RunnableTask("task2"));
        // 22:54:42.524 [     main] task2 start()
        // 22:54:43.535 [     main] task2 end()
        es.execute(new RunnableTask("task3"));

        es.close();
    }
}
