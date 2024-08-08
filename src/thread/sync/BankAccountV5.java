package thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV5 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV5(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("withdraw start: " + getClass().getSimpleName());

        // 락 획득을 시도하고, 즉시 성공 여부 반환
        if (!lock.tryLock()) {
            log("fail to get lock");
            return false;
        }

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
