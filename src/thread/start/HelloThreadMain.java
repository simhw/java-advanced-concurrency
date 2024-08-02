package thread.start;

public class HelloThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": start() start");
        // 스택 공간을 할당 받고 스레드가 작동
        // Thread-0 스레드가 run() 메서드 실행 
        helloThread.start();
        System.out.println(Thread.currentThread().getName() + ": start() end");

        System.out.println(Thread.currentThread().getName() + ": main() end");

    }
}
