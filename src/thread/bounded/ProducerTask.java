package thread.bounded;

import static util.MyLogger.log;

public class ProducerTask implements Runnable {

    private final BoundedQueue queue;
    private final String request;

    public ProducerTask(BoundedQueue queue, String request) {
        this.queue = queue;
        this.request = request;
    }

    @Override
    public void run() {
        log("try produce " + request + ", queue: " + queue);
        queue.put(request);
        log("success produce: " + request + ", queue: " + queue);
    }
}
