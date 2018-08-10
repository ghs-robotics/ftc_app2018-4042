package org.firstinspires.ftc.teamcode.control;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.io.output.Log;

/**
 * Singleton
 *
 * Contains various year-specific functionality
 * Takes various parameters and the current control state and
 * calls the correct functions in the ActuatorManager
 */
public class Operations {

    public static Operations self;

    Operations() {  }

    /**
     * Singleton functionality
     * Gets the instance of this class
     * @return The only extant instance of the class
     */
    public static Operations get() {
        if (self == null) {
            self = new Operations();
        }
        return self;
    }

    // Sample year-specific code: //
    public void print() {
        Log.debug("a");
        Statics.telemetry().addData("a", "a");
    }
    // Sample ends //
}
