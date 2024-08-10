package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;

public class BoundedQueueV3 implements BoundedQueue {

    private final int max;
    private final Queue<String> queue = new ArrayDeque<>();

    public BoundedQueueV3(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            log("full queue, wait");
            try {
                // RUNNABLE → WAITING + 락 반납
                // 스레드 대기 집합에서 대기
                wait();
                log("not full queue, notified");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        queue.offer(data);
        notify();
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("empty queue, wait");
            try {
                wait();
                log("not empty queue, notified");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String data = queue.poll();
        // 스레드 대기 집합에 소비자 스레드만 있는 경우 불필요한 호출 발생
        notify();
        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
