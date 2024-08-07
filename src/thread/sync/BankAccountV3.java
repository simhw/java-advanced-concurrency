package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV3 implements BankAccount {

    private int balance;

    public BankAccountV3(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("withdraw start: " + getClass().getSimpleName());

        synchronized (this) {
            // 잔고가 출금액 보다 적으면, 중지
            if (balance < amount) {
                log("withdraw fail: " + ", amount: " + amount + ", balance: " + balance);
                return false;
            }

            // 잔고가 출금액 보다 많으면, 실행
            // 출금에 소요되는 시간 가정
            sleep(1000);
            this.balance -= amount;
        }

        log("withdraw success, amount: " + amount + ", balance: " + balance);
        log("withdraw end: " + getClass().getSimpleName());
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return this.balance;
    }
}
