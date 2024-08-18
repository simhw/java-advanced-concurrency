package thread.collection.simple;

import static util.MyLogger.log;

public class SimpleListMainV2 {
    public static void main(String[] args) throws InterruptedException {
        SimpleList basicList = new BasicList();
        System.out.println(basicList.getClass());
        test(basicList);

        SimpleList syncList = new SyncList();
        System.out.println(syncList.getClass());
        test(syncList);

        SimpleList proxyList = new SyncProxyList(new BasicList());
        System.out.println(proxyList.getClass());
        test(proxyList);

    }


    public static void test(SimpleList list) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                list.add("A");
                log("add A");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                list.add("B");
                log("add B");
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(list);
    }

}
