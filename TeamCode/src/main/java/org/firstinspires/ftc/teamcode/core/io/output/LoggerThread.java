package org.firstinspires.ftc.teamcode.core.io.output;

import org.firstinspires.ftc.teamcode.core.Statics;
import org.firstinspires.ftc.teamcode.core.io.input.IOUtils;

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
    private File file;

    //Volatile makes this parameter thread-safe
    public static volatile boolean RUN;

    LoggerThread(ConcurrentLinkedQueue<LogMessage> q) {
        LoggerThread.RUN = true;

        this.q = q;
        int i = 0;
        do {
            i++;
        } while (new File(IOUtils.FILE_ROOT,"log" + i + ".txt").isFile());
        file = new File(IOUtils.FILE_ROOT, "log" + i + ".txt");
        Statics.telemetry().log().add("file: " + file);
    }

    @Override
    public void run() {
        while (RUN) {
            if (!q.isEmpty()) {
                LogMessage message = q.poll();
                //Statics.telemetry().log().add(message.toString());
                printMessage(message);
            }
        }
    }

    private void printMessage(LogMessage message) {
        try {
            PrintWriter printer = new PrintWriter(new FileWriter(file, true));
            printer.println(message.toString());
            printer.close();
        } catch (IOException ex) {  }
    }
}
