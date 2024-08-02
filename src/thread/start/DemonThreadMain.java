package thread.start;

public class DemonThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        DemonThread demonThread = new DemonThread();
        // 데몬 스레드 설정
        demonThread.setDaemon(true);
        demonThread.start();

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }

    static class DemonThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": run() start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + ": run() end");
        }
    }
}
