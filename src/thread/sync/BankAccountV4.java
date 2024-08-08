package thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV4 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV4(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("withdraw start: " + getClass().getSimpleName());

        // 락을 획득하지 못하면 WAITING 상태가 되고, 대기 큐에서 관리
        // LockSupoort.park() 내부에서 호출
        lock.lock();

        try {
            // 잔고가 출금액 보다 적으면, 중지
            if (balance < amount) {
                log("withdraw fail" + ", amount: " + amount + ", balance: " + balance);
                return false;
            }

            // 잔고가 출금액 보다 많으면, 실행
            // 출금에 소요되는 시간 가정
            sleep(1000);
            this.balance -= amount;

        } finally {
            // LockSupoort.unpark(thread) 내부에서 호출
            // RUNNABLE 상태가 되면서 스레드는 락 획득을 시도
            lock.unlock();
        }

        log("withdraw success, amount: " + amount + ", balance: " + balance);
        log("withdraw end: " + getClass().getSimpleName());
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();

        try {
            return this.balance;
        } finally {
            lock.unlock();
        }
    }
}
