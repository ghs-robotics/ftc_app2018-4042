package org.firstinspires.ftc.teamcode.io.output;

import org.firstinspires.ftc.teamcode.io.input.IOUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentLinkedQueue;

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
            System.out.println("i: " + i);
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
