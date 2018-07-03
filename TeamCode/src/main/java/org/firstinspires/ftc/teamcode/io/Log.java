package org.firstinspires.ftc.teamcode.io;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Log {

    public static final File LOG_ROOT = new File ("./storage/emulated/0/bluetooth/");

    // Stores things to be logged, uses a Concurrent queue
    // to avoid race conditions with multithreading
    private ConcurrentLinkedQueue<LogMessage> logs;

    private Runnable loggerThread;

    public Log() {
        logs = new ConcurrentLinkedQueue<LogMessage>();

        loggerThread = new LoggerThread(logs);
        new Thread(loggerThread).start();
    }

    /**
     * Logs a debug message, along with a timestamp
     * Appropriate for TEMPORARY debugging messages
     * @param message The message to log
     */
    public void debug(String message) {
        logs.offer(new LogMessage(LogMessage.Priority.DEBUG, message));
    }

    /**
     * Logs an info message, along with a timestamp
     * Appropriate for WELL-FORMATTED, PERMANENTLY USEFUL technical output
     * @param message The message to log
     */
    public void info(String message) {
        logs.offer(new LogMessage(LogMessage.Priority.INFO, message));
    }

    /**
     * Logs a warning message, along with a timestamp
     * Appropriate for unexpected but not inherently problematic situations
     * @param message The message to log
     */
    public void warn(String message) {
        logs.offer(new LogMessage(LogMessage.Priority.WARN, message));
    }

    /**
     * Logs an error message, along with a timestamp
     * Appropriate for problematic, exceptional, or supposedly impossible situations
     * @param message The message to log
     */
    public void error(String message) {
        logs.offer(new LogMessage(LogMessage.Priority.ERROR, message));
    }

    /**
     * Logs a fatal error message, along with a timestamp
     * Appropriate for situations where the code can no longer continue to function
     * @param message The message to log
     */
    public void fatal(String message) {
        logs.offer(new LogMessage(LogMessage.Priority.FATAL, message));
    }
}
