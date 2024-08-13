package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BoundedQueueV6_4 implements BoundedQueue {

    private final BlockingQueue<String> queue;

    public BoundedQueueV6_4(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        // java.lang.IllegalStateException
        queue.add(data);
    }

    @Override
    public String take() {
        // java.util.NoSuchElementException
        return queue.remove();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
