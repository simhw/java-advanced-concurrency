package thread.executor.test;


import java.util.concurrent.ExecutionException;

public class NewOrderServiceMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String orderNo = "order#1234";
        NewOrderService newOrderSerivce = new NewOrderService();
        newOrderSerivce.order(orderNo);
        /**
         23:16:36.424 [pool-1-thread-2] ShippingWork: order#1234
         23:16:36.424 [pool-1-thread-1] InventoryWork: order#1234
         23:16:36.424 [pool-1-thread-3] AccountingWork: order#1234
         23:16:37.437 [     main] 모든 주문 처리가 성공적으로 완료되었습니다.
         */
    }

}
