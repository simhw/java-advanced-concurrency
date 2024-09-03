package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

public class RejectMainV2 {
    public static void main(String[] args) {
        // 거절된 작업은 무시되고 예외가 발생하지 않음
        ExecutorService es = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.DiscardPolicy());

        es.execute(new RunnableTask("task1"));
        es.execute(new RunnableTask("task2"));
        es.execute(new RunnableTask("task3"));

        es.close();
    }
}
