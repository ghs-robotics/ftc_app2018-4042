package org.firstinspires.ftc.teamcode.control;

/**
 * Parses joystick inputs and calls the appropriate function in Operations
 */
public class TeleOpInputManager {

    private Operations teleOps;

    private static TeleOpInputManager self;

    private TeleOpInputManager() {
        teleOps = Operations.get();
    }

    /**
     * Singleton functionality
     * Gets the instance of this class
     * @return The only extant instance of the class
     */
    public static TeleOpInputManager get() {
        if (self == null) {
            self = new TeleOpInputManager();
        }
        return self;
    }

    /**
     * TODO: WRITE THIS FUNCTION (once the season starts; it's year-specific)
     *
     * Is called repeatedly every loop cycle during teleop
     *
     * Reads from the gamepad and calls the correct functions in Operations
     * based on the gampepad inputs
     */
    public void update() {

    }
}
