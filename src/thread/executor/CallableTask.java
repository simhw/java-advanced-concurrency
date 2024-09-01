package thread.executor;

import java.util.concurrent.Callable;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class CallableTask implements Callable<Integer> {

    public String name;
    public int sleepMs = 1000;

    public CallableTask(String name) {
        this.name = name;
    }

    public CallableTask(String name, int sleepMs) {
        this.name = name;
        this.sleepMs = sleepMs;
    }

    @Override
    public Integer call() throws Exception {
        log("call() start");
        sleep(sleepMs);
        log("call() end");
        return sleepMs;
    }
}
