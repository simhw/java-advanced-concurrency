package thread.start;

public class BadThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": start() start");
        // main 스레드가 run() 메서드 실행
        // main 스레드가 사용하는 스택 영역에 run() 적재
        helloThread.run();
        System.out.println(Thread.currentThread().getName() + ": start() end");

        System.out.println(Thread.currentThread().getName() + ": main() end");

    }
}
