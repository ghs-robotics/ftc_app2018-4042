package org.firstinspires.ftc.teamcode.control;

/**
 * Singleton
 *
 * The same as Operations, but contains auto-specific functionality also
 */
public class AutoOperations extends Operations {

    private static AutoOperations self;

    private AutoOperations() {  }

    /**
     * Singleton functionality
     * Gets the instance of this class
     * @return The only extant instance of the class
     */
    public static AutoOperations get() {
        if (self == null) {
            self = new AutoOperations();
        }
        return self;
    }
}
