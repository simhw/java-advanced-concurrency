package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;

public class BoundedQueueV4 implements BoundedQueue {

    // 락과 락 획득을 대기하는 스레드 관리 큐
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private final int max;
    private final Queue<String> queue = new ArrayDeque<>();

    public BoundedQueueV4(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {
        lock.lock();

        try {
            while (queue.size() == max) {
                log("full queue, wait");
                try {
                    condition.await();
                    log("not full queue, notified");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            queue.offer(data);
            condition.signal();

        } finally {
            lock.unlock();
        }

    }

    @Override
    public String take() {
        lock.lock();

        try {
            while (queue.isEmpty()) {
                log("empty queue, wait");
                try {
                    condition.await();
                    log("not empty queue, notified");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            String data = queue.poll();
            condition.signal();
            return data;

        } finally {
            lock.unlock();
        }

    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
