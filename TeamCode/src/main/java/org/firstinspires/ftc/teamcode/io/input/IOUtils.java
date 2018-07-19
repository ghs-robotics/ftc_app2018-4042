package org.firstinspires.ftc.teamcode.io.input;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Holds assorted utility functions for input/output work
 */
public class IOUtils {

    public static final File FILE_ROOT = new File ("./storage/emulated/0/bluetooth/");
    //public static final File FILE_ROOT = new File ("C:\\Users\\Hazel\\Robotics\\io");

    /**
     * Gets the stack trace of an exception as a string
     * @param ex The exception to parse
     * @return The stack trace of the exception
     */
    public static String getStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(new StringWriter());
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
