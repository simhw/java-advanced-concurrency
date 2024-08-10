package thread.bounded;

import static util.MyLogger.log;

public class ConsumerTask implements Runnable {

    private final BoundedQueue queue;

    public ConsumerTask(BoundedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        log("try take data" + ", queue: " + queue);
        String data = queue.take();
        log("success take: " + data + ", queue: " + queue);
    }
}
