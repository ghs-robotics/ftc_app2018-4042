package org.firstinspires.ftc.teamcode.io.output;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * General logging functionality. Takes messages and stores on a file on the phone.
 * Path to logs is of the format ./storage/emulated/0/bluetooth/log1234.txt
 *
 * Contains 5 levels of logging: debug, info, warn, error, and fatal.
 * Logging calls should be of the format: Log.debug("example");
 */
public class Log {

    private static Log self = null;

    // Stores things to be logged, uses a Concurrent queue
    // to avoid race conditions with multithreading
    private ConcurrentLinkedQueue<LogMessage> logs;

    private Runnable loggerThread;

    private Log() {
        logs = new ConcurrentLinkedQueue<>();

        loggerThread = new LoggerThread(logs);
        new Thread(loggerThread).start();
    }

    /**
     * Singleton handling class
     * We need a log instance, since the log wraps the non-static thread
     * @return The instance of the log
     */
    private static Log log() {
        if (self == null) {
            self = new Log();
        }
        return self;
    }

    /**
     * Logs a debug message, along with a timestamp
     * Appropriate for TEMPORARY debugging messages
     * @param message The message to log
     */
    public static void debug(String message) {
        Log.log().logs.offer(new LogMessage(LogMessage.Priority.DEBUG, message));
    }

    /**
     * Logs an info message, along with a timestamp
     * Appropriate for WELL-FORMATTED, PERMANENTLY USEFUL technical output
     * @param message The message to log
     */
    public static void info(String message) {
        Log.log().logs.offer(new LogMessage(LogMessage.Priority.INFO, message));
    }

    /**
     * Logs a warning message, along with a timestamp
     * Appropriate for unexpected but not inherently problematic situations
     * @param message The message to log
     */
    public static void warn(String message) {
        Log.log().logs.offer(new LogMessage(LogMessage.Priority.WARN, message));
    }

    /**
     * Logs an error message, along with a timestamp
     * Appropriate for problematic, exceptional, or supposedly impossible situations
     * Automatically adds a stack trace to the print, pass false as the second argument
     * if this is undesirable behavior
     * @param message The message to log
     */
    public static void error (String message) {
        error(message, true);
    }

    public static void error(String message, boolean stackTrace) {
        if (stackTrace) { message += Arrays.toString(Thread.currentThread().getStackTrace()).
                replace(", ", "\n"); }
        Log.log().logs.offer(new LogMessage(LogMessage.Priority.ERROR, message));
    }

    /**
     * Logs a fatal error message, along with a timestamp
     * Appropriate for situations where the code can no longer continue to function
     * Automatically adds a stack trace to the print, pass false as the second argument
     * if this is undesirable behavior
     * @param message The message to log
     */
    public static void fatal(String message) {
        fatal(message, true);
    }

    public static void fatal(String message, boolean stackTrace) {
        if (stackTrace) { message += Arrays.toString(Thread.currentThread().getStackTrace()); }
        Log.log().logs.offer(new LogMessage(LogMessage.Priority.FATAL, message));
    }
}
