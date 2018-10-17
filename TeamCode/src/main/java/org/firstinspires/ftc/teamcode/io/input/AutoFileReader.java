package org.firstinspires.ftc.teamcode.io.input;

import org.firstinspires.ftc.teamcode.io.output.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads from auto files line-by-line.
 * Contains a method to get the last read line, and a method to get the next one.
 *
 * Singleton
 */
public class AutoFileReader {

    private static AutoFileReader self = null;
    private BufferedReader reader;
    private File file;

    private String currLine = null;

    /**
     * Sets the file to read from. Call this at the start of an auto!
     * @param file The name of the file to read from, NOT including the root directory
     */
    public void setFile(String file) {
        try {
            this.file = new File(IOUtils.FILE_ROOT, file);
            reader = new BufferedReader(new FileReader(this.file));
        } catch (FileNotFoundException ex) {
            Log.error("Could not find specified auto file: " + this.file);
        }
    }

    private AutoFileReader() { }

    /**
     * Gets an instance of this Singleton
     * When you want to change the file it points to,
     * call get() then setFile(file)
     * @return The instance of AutoFileReader
     */
    public static AutoFileReader get() {
        if (self == null) {
            self = new AutoFileReader();
        }
        return self;
    }

    /**
     * DOES NOT advance the operation, just returns the last fetched one
     * @return The last line fetched
     */
    public String getCurrOp() {
        return currLine;
    }

    /**
     * Advances to the next operation
     * @return The new operation
     */
    public String advanceOp() {
        try {
            currLine = reader.readLine();
            return currLine;
            //return "";
        } catch (IOException ex) {
            Log.error("Couldn't read next line of file " + this.file);
        }
        return "";
    }
}
