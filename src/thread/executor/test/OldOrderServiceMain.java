package thread.executor.test;


public class OldOrderServiceMain {
    public static void main(String[] args) {
        String orderNo = "order#1234";
        OldOrderService oldOrderService = new OldOrderService();
        oldOrderService.order(orderNo);
        /**
         22:59:30.606 [     main] InventoryWork: order#1234
         22:59:31.617 [     main] ShippingWork: order#1234
         22:59:32.621 [     main] AccountingWork: order#1234
         22:59:33.627 [     main] 모든 주문 처리가 성공적으로 완료되었습니다.
         */
    }

}
