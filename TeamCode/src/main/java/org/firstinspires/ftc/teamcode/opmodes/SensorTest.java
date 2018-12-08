package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.balldrive.DriveSubsystem;
import org.firstinspires.ftc.teamcode.core.iobuiltin.GamepadExtended;
import org.firstinspires.ftc.teamcode.core.structure.ClassHolder;
import org.firstinspires.ftc.teamcode.core.OpModeExtended;
import org.firstinspires.ftc.teamcode.core.structure.Registry;
import org.firstinspires.ftc.teamcode.core.structure.SensorManager;
import org.firstinspires.ftc.teamcode.core.structure.Subsystem;

import java.util.ArrayList;

@TeleOp(name = "Sensor Test", group = "tele")
public class SensorTest extends OpModeExtended {

    public OpModeExtended.InputControlManager getInputControlManager() {
        return new SensorTest.TICM();
    }

    public ClassHolder getClassHolder() {
        return new Holder(this);
    }

    public class TICM extends OpModeExtended.TeleInputControlManager {
        Subsystem drive;
        Servo servo;
        DcMotorEx motor;
        SensorManager gyro, ultrasonic;
        double[][] measuredMatrix = new double[][]{};
        double servoLoc;
        double px;
        double py;
        double vx;
        double vy;
        double theta;
        double r;
        double dt;
        double errorPx;
        double errorPy;
        double errorVx;
        double errorVy;
        double[][] previousPrediction = new double[][]{
                { px, py, 0, 0 }
        };

        double[][] predictedError = new double[][]{
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };

        double[][] transtionState = new double[][]{
                { 1,  0, 0, 0 },
                { 0,  1, 0, 0 },
                { dt, 0, 1, 0 },
                { 0, dt, 0, 1 }
        };

        double[][][] previousKalmanStateAndError = new double[][][]{predictedError, previousPrediction};

        double[][] stateScalar = new double[][]{
                { 1, 0 },
                { 0, 1 },
                { 0, 0 },
                { 0, 0 }
        };

        double[][] measurementCovariance = new double[][]{
                { r, 0},
                { 0, r}
        };

        double[][] identityMatrix = new double[][]{
                {1, 0},
                {0, 1}
        };

        public void teleinit() {
            drive = Registry.getSubsystemByName("driveSubsystem");
            gyro = Registry.getSensorManagerByName("gyro");
            ultrasonic = Registry.getSensorManagerByName("ultrasonix");

            servo = drive.context.hardwareMap.servo.get("pawl");
            servoLoc = 0;

            motor = (DcMotorEx) drive.context.hardwareMap.dcMotor.get("lift");

            drive.setting("mode", DriveSubsystem.Mode.MANUAL_LRS);
            drive.setting("encoderPrint", true);

        }
        public void teleupdate() {
            telemetry.addData("A", "Encoders");

            runDrive();
            moveServo();
            moveMotor();

            telemetry.addData("gyro heading", gyro.getLastNValues(1)[0]);
            telemetry.addData("servo", servoLoc);

            measuredMatrix = new double [][]{
                {ultrasonic.getCM() /*x direction*/, ultrasonic.getCM() /*y direction*/, /*x direction motor velocity*/vx,
                        vy /*y direction motor velocity*/}
            };
            double[][][] KalmanStateAndError =  ultrasonic.runKalmanFilter(previousKalmanStateAndError, transtionState,
                    stateScalar, measurementCovariance, identityMatrix, measuredMatrix);
            telemetry.addData("State and Error", KalmanStateAndError);


        }

        private void runDrive() {
            if (gamepadExtended1.a.equals(GamepadExtended.ButtonState.DOWN)) {
                drive.setting("manualL", .3);
                drive.setting("manualR", .3);
                drive.setting("manualS", .3);
            } else {
                drive.setting("manualL", 0);
                drive.setting("manualR", 0);
                drive.setting("manualS", 0);
            }
        }

        private void moveServo() {
            if (gamepadExtended1.dpad_up.equals(GamepadExtended.ButtonState.DOWNING)) {
                servoLoc += .1;
                servo.setPosition(servoLoc);
            } else if (gamepadExtended1.dpad_down.equals(GamepadExtended.ButtonState.DOWNING)) {
                servoLoc -= .1;
                servo.setPosition(servoLoc);
            } else if (gamepadExtended1.dpad_left.equals(GamepadExtended.ButtonState.DOWNING)) {
                servoLoc -= .01;
                servo.setPosition(servoLoc);
            } else if (gamepadExtended1.dpad_right.equals(GamepadExtended.ButtonState.DOWNING)) {
                servoLoc += .01;
                servo.setPosition(servoLoc);
            }
        }

        private void moveMotor() {
            if (gamepadExtended1.left_trigger > .1) {
                motor.setPower(-gamepadExtended1.left_trigger);
            } else if (gamepadExtended1.right_trigger > .1) {
                motor.setPower(gamepadExtended1.right_trigger);
            } else {
                motor.setPower(0);
            }
        }
    }
}
