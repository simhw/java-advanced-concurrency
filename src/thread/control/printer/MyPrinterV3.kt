package thread.control.printer

import thread.util.MyLogger.log
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue

fun main() {
    val printer = Printer();
    val thread = Thread(printer, "printer");
    thread.start();

    val scanner = Scanner(System.`in`);

    while (true) {
        val input = scanner.nextLine();
        log("input: $input");

        if (input.lowercase(Locale.getDefault()) == "q") {
            thread.interrupt();
            break
        }
        printer.addJob(input);
    }
}

class Printer : Runnable {

    var jobQueue = ConcurrentLinkedQueue<String>();

    override fun run() {
        while (true) {
            if (jobQueue.isEmpty()) {
                continue;
            }

            log("output: ${jobQueue.poll()}: $jobQueue");
            Thread.sleep(1000);
        }

        log("exit printer");
    }

    fun addJob(str: String) {
        jobQueue.add(str);
    }
}