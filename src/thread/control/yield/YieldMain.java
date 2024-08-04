package thread.control.yield;

public class YieldMain {

    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                // RUNNABLE → TIMED_WAITING → RUNNABLE
                // sleep(1);

                // RUNNING → READY
                // 실행 중인 스레드가 CPU를 양보하도록 힌트
                Thread.yield();
            }
        }
    }
}
