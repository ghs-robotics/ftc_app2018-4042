package org.firstinspires.ftc.teamcode.control;

import org.firstinspires.ftc.teamcode.io.input.AutoFileReader;

/**
 * Parses auto files and calls the appropriate functions in AutoOperations
 * TODO: WRITE THIS CLASS
 */
public class AutoInputManager {

    private AutoFileReader reader;

    private AutoOperations autoOps;

    public static AutoInputManager self;

    private AutoInputManager(String autoFileName) {
        reader = AutoFileReader.get();
        reader.setFile(autoFileName);

        autoOps = AutoOperations.get();
    }

    /**
     * Singleton functionality.
     * Gets the instance of this class
     * @param autoFileName The name of the file to read from
     * @return The only extant instance of the class
     */
    public static AutoInputManager get(String autoFileName) {
        if (self == null) {
            self = new AutoInputManager(autoFileName);
        }
        return self;
    }

    /**
     * TODO: WRITE THIS FUNCTION
     *
     * Is called repeatedly every loop cycle during auto
     *
     * Reads from a file (possibly using AutoFileReader?) and calls
     * the requisite functions in AutoOperations.
     *
     * This is the place where the file parsing actually happens, hence
     * this is where TeaScript might be implemented.
     */
    public void update() {

    }
}
