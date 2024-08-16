package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLockBad {

    private volatile boolean lock = false;

    public void lock() {
        log("try to get lock");
        // 원자적이지 않은 로직 실행
        while (true) {
            // 락 사용 여부 확인
            if (!lock) {
                sleep(100);
                // 락의 값 변경
                lock = true;
                break;
            } else {
                log("fail to get lock");
            }
        }
        log("success to get lock");
    }

    public void unlock() {
        lock = false;
        log("success to return lock");
    }
}
