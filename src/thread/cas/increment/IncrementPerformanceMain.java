package thread.cas.increment;

public class IncrementPerformanceMain {

    public static final long COUNT = 100_000_000;

    public static void test(IncrementInteger incrementInteger) throws InterruptedException {
        long start = System.currentTimeMillis();

        for (int i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }

        long end = System.currentTimeMillis();
        System.out.println(incrementInteger.getClass().getSimpleName() + " ms: " + (end - start));
    }

    public static void main(String[] args) throws InterruptedException {
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new SyncInteger());
        test(new MyAtomicInteger());
    }
}
