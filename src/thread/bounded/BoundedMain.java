package thread.bounded;

import java.util.ArrayList;
import java.util.List;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedMain {
    public static void main(String[] args) {

        BoundedQueue queue = new BoundedQueueV6_4(2);

        producerFirst(queue);
//        consumerFirst(queue);
    }

    private static void producerFirst(BoundedQueue queue) {
        log("start publish producer first: " + queue.getClass().getSimpleName());

        List<Thread> threads = new ArrayList<>();
        startProducer(queue, threads);
        printAllState(queue, threads);

        startConsumer(queue, threads);
        printAllState(queue, threads);

        log("end publish producer first: " + queue.getClass().getSimpleName());
    }

    private static void consumerFirst(BoundedQueue queue) {
        log("start publish consumer first: " + queue.getClass().getSimpleName());

        List<Thread> threads = new ArrayList<>();
        startConsumer(queue, threads);
        printAllState(queue, threads);

        startProducer(queue, threads);
        printAllState(queue, threads);

        log("end publish consumer first: " + queue.getClass().getSimpleName());
    }

    private static void startProducer(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("start producer");

        for (int i = 1; i <= 3; i++) {
            ProducerTask task = new ProducerTask(queue, "data" + i);
            Thread producer = new Thread(task, "producer" + i);
            threads.add(producer);

            producer.start();
            sleep(100);
        }
    }

    private static void startConsumer(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("start consumer");

        for (int i = 1; i <= 3; i++) {
            Thread consumer = new Thread(new ConsumerTask(queue), "consumer" + i);
            threads.add(consumer);

            consumer.start();
            sleep(100);
        }
    }

    private static void printAllState(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("print now state: " + queue);

        for (Thread thread : threads) {
            log(thread.getName() + ": " + thread.getState());
        }
    }
}
