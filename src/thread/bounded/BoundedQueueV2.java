package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV2 implements BoundedQueue {

    private final int max;
    private final Queue<String> queue = new ArrayDeque<>();

    public BoundedQueueV2(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            log("full queue, wait");
            // 스레드가 락을 가지고 있는 상태에서, 큐에 빈자리가 나올 때까지 대기
            sleep(100);
        }

        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("empty queue, wait");
            // 스레드가 락을 가지고 있는 상태에서, 큐에 데이터가 들어올 때까지 대기
            sleep(100);
        }
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
