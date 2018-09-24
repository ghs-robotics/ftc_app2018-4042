package org.firstinspires.ftc.teamcode.core.highlevel;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.core.Statics;
import org.firstinspires.ftc.teamcode.core.Registry;

import static teascript.Nice.executeAction;

/**
 * Oversees both auto and teleop.
 * Manages transitions between the two of them, and calls the requisite update() functions
 * on the AutoInputManager and TeleOpInputManager.
 *
 * OpModes should interface solely with this class.
 *
 * Singleton
 */
public class InputControlManager {

    public static InputControlManager self = null;

    private static boolean RUN_AUTO;

    /**
     * Set to false to turn off the timer
     */
    public static boolean USE_TIMER;

    private ElapsedTime timer;
    private static final double AUTO_PERIOD = 30.0;
    private static final double TRANSITION_PERIOD = 8.0;
    private static final double TELE_OP_PERIOD = 90.0;

    private InputControlManager() throws javax.xml.transform.TransformerFactoryConfigurationError{
        timer = new ElapsedTime();
        USE_TIMER = true;

        /* TODO: Add sensors using Registry.addSensor(); */
        Registry.initSensors();
        Registry.initSubsystems();
    }

    /**
     * TELE-OP GET: sets this to teleop
     * Singleton functionality
     * Gets the instance of this class
     * @return The only extant instance of the class
     */
    public static InputControlManager get() {
        if (self == null) {
            self = new InputControlManager();
        }
        InputControlManager.RUN_AUTO = false;
        return self;
    }

    /**
     * AUTO GET: sets this to auto
     * Singleton functionality
     * Gets the instance of this class
     * @return The only extant instance of the class
     */
    public static InputControlManager get(boolean auto) {
        InputControlManager selfTemp = InputControlManager.get();

        InputControlManager.RUN_AUTO = true;

        return selfTemp;
    }

    public void update() {
        // Allow turning off the timer
        if (USE_TIMER) {
            if (RUN_AUTO && timer.seconds() < AUTO_PERIOD) {
                Statics.telemetry().addData("op mode", "auto");
                updateSubs(true);
            }
            // Run teleop if:
            // You're running teleop and not at the end of teleop OR
            // You're running auto and not at the end of teleop and not in the auto period
            else if ((!RUN_AUTO && timer.seconds() < TELE_OP_PERIOD) ||
                    (RUN_AUTO && timer.seconds() < (TELE_OP_PERIOD + TRANSITION_PERIOD + AUTO_PERIOD)
                            && timer.seconds() > AUTO_PERIOD + TRANSITION_PERIOD)) {
                Statics.telemetry().addData("op mode", "teleop");
                updateSubs(false);
            } else {
                Statics.telemetry().addData("op mode", "not running");
            }
        } else {
            if (RUN_AUTO) {
                Statics.telemetry().addData("op mode", "auto");
                updateSubs(true);
            }
            else {
                Statics.telemetry().addData("op mode", "teleop");
                updateSubs(false);
            }
        }
    }

    /**
     * Updates each subsystem
     */
    private void updateSubs(boolean auto) {
        Registry.updateSensors();
        Registry.updateData();
        if (auto) executeAction("USERFUN(main())");
        Registry.updateActuators();
    }
}
