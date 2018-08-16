package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.DcMotor;

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
    private DcMotor mLeft, mRight, mStrafe;

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
        self.mLeft = Statics.hardwareMap().dcMotor.get("motorLF");
        self.mRight = Statics.hardwareMap().dcMotor.get("motorRF");
        self.mStrafe = Statics.hardwareMap().dcMotor.get("motorStrafe");
        return self;
    }

    // Sample year-specific code: //
    public void drive(double x, double y, double r) {
        self.mLeft.setPower(r - y);
        self.mRight.setPower(y + r);
        self.mStrafe.setPower(x);
    }
    // Sample ends //
}
