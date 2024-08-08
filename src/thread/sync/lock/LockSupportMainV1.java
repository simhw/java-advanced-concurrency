package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV1 {

    public static void main(String[] args) {
        Runnable park = new Runnable() {
            @Override
            public void run() {
                log("park() start");
                // RUNNABLE 상태에서 WAITING 상태로 전환
                LockSupport.park();
                log("park() end");
                log("Thread-1 state: " + Thread.currentThread().getState());
                log("Thread-1 interrupted: " + Thread.currentThread().isInterrupted());
            }
        };

        Thread thread1 = new Thread(park, "Thread-1");
        thread1.start();

        sleep(1000);

        log("Thread-1 state: " + thread1.getState());
        LockSupport.unpark(thread1);
        /**
         * 02:33:18.747 [ Thread-1] park() start
         * 02:33:19.712 [     main] Thread-1 state: WAITING
         * 02:33:19.714 [ Thread-1] park() end
         * 02:33:19.715 [ Thread-1] Thread-1 state: RUNNABLE
         * 02:33:19.723 [ Thread-1] Thread-1 interrupted: false
         */

        // thread1.interrupt();
        /**
         * 02:33:51.299 [ Thread-1] park() start
         * 02:33:52.266 [     main] Thread-1 state: WAITING
         * 02:33:52.267 [ Thread-1] park() end
         * 02:33:52.268 [ Thread-1] Thread-1 state: RUNNABLE
         * 02:33:52.275 [ Thread-1] Thread-1 interrupted: true
         */


    }
}
