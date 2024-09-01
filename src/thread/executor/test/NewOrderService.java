package thread.executor.test;

import java.util.List;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class NewOrderService {

    private final ExecutorService es = Executors.newFixedThreadPool(10);

    public void order(String orderNo) throws InterruptedException, ExecutionException {
        InventoryWork inventoryWork = new InventoryWork(orderNo);
        ShippingWork shippingWork = new ShippingWork(orderNo);
        AccountingWork accountingWork = new AccountingWork(orderNo);

        List<Callable<Boolean>> tasks = List.of(inventoryWork, shippingWork, accountingWork);

        boolean result = true;
        for (Future<Boolean> future : es.invokeAll(tasks)) {
            if (!future.get()) {
                result = false;
            }
        }

        if (result) {
            log("모든 주문 처리가 성공적으로 완료되었습니다.");
        } else {
            log("일부 작업이 실패했습니다.");
        }

        es.close();
    }

    static class InventoryWork implements Callable<Boolean> {
        private final String orderNo;

        public InventoryWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("InventoryWork: " + orderNo);
            sleep(1000);

            return true;
        }
    }


    static class ShippingWork implements Callable<Boolean> {
        private final String orderNo;

        public ShippingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("ShippingWork: " + orderNo);
            sleep(1000);

            return true;
        }
    }

    static class AccountingWork implements Callable<Boolean> {
        private final String orderNo;

        public AccountingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("AccountingWork: " + orderNo);
            sleep(1000);

            return true;
        }
    }

}
