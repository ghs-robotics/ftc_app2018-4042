package org.firstinspires.ftc.teamcode.io.output;

import org.firstinspires.ftc.teamcode.io.input.IOUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Actually handles the logging to a file.
 * Regularly pings a ConcurrentLinkedQueue and prints the log messages in it to a file.
 *
 * This is in a separate thread so it doesn't slow down the rest of the code.
 */
public class LoggerThread implements Runnable {

    private ConcurrentLinkedQueue<LogMessage> q;
    private PrintWriter printer;

    //Volatile makes this parameter thread-safe
    public static volatile boolean RUN = true;

    LoggerThread(ConcurrentLinkedQueue<LogMessage> q) {
        this.q = q;
        try {
            int i = 0;
            do {
                i++;
            } while (new File("log" + i + ".txt").isFile());
            printer = new PrintWriter(new FileWriter(new File(IOUtils.FILE_ROOT, "log" + i + ".txt")));
        } catch (IOException ex) {
            //TODO: exception handling
        }
    }

    @Override
    public void run() {
        while (RUN) {
            if (!q.isEmpty()) {
                LogMessage message = q.poll();
                printer.print(message.toString());
            }
        }
        stop();
    }

    private void stop() {
        printer.close();
    }
}
