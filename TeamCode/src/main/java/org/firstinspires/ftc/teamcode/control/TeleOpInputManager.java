package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.io.input.GamepadAdv;

/**
 * Parses joystick inputs and calls the appropriate function in Operations
 */
public class TeleOpInputManager {

    private Operations teleOps;

    private GamepadAdv gamepad1;
    private GamepadAdv gamepad2;

    public static TeleOpInputManager self;

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
            self.gamepad1 = new GamepadAdv(Statics.gamepad1());
            self.gamepad2 = new GamepadAdv(Statics.gamepad2());
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
        gamepad1.update();
        gamepad2.update();

        // Sample year-specific code: //
        if (gamepad1.a()) {
            teleOps.print();
        }
        // Sample ends //

        Statics.telemetry().update();
    }
}
