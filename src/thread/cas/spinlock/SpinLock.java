package thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static util.MyLogger.log;

public class SpinLock {

    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        log("try to get lock");
        // 락 사용 여부 확인 + 락의 값 변경
        while (!lock.compareAndSet(false, true)) {
            log("fail to get lock");
        }
        log("success to get lock");
    }

    public void unlock() {
        lock.set(false);
        log("success to return lock");
    }
}
/**
 * 23:58:51.761 [ Thread-1] try to get lock
 * 23:58:51.761 [ Thread-0] try to get lock
 * 23:58:51.767 [ Thread-1] success to get lock
 * 23:58:51.767 [ Thread-0] fail to get lock
 * 23:58:51.767 [ Thread-1] start to business logic
 * 23:58:51.767 [ Thread-0] fail to get lock
 * 23:58:51.767 [ Thread-1] success to return lock
 * 23:58:51.767 [ Thread-0] success to get lock
 * 23:58:51.768 [ Thread-0] start to business logic
 * 23:58:51.768 [ Thread-0] success to return lock
 */