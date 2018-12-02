package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.balldrive.DriveSubsystem;
import org.firstinspires.ftc.teamcode.balldrive.LiftSubsystem;
import org.firstinspires.ftc.teamcode.core.iobuiltin.GamepadExtended;
import org.firstinspires.ftc.teamcode.core.structure.ClassHolder;
import org.firstinspires.ftc.teamcode.core.OpModeExtended;
import org.firstinspires.ftc.teamcode.core.structure.Registry;
import org.firstinspires.ftc.teamcode.core.structure.SensorManager;
import org.firstinspires.ftc.teamcode.core.structure.Subsystem;

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
        Servo pawl;
        SensorManager gyro;
        double servoLoc;

        public void teleinit() {
            drive = Registry.getSubsystemByName("driveSubsystem");
            pawl = drive.context.hardwareMap.servo.get("pawl");
            servoLoc = 0;
            gyro = Registry.getSensorManagerByName("gyro");

            drive.setting("mode", DriveSubsystem.Mode.MANUAL_LRS);
            drive.setting("encoderPrint", true);
        }
        public void teleupdate() {
            telemetry.addData("A", "Encoders");
            if (gamepadExtended1.a.equals(GamepadExtended.ButtonState.DOWN)) {
                drive.setting("manualL", .3);
                drive.setting("manualR", .3);
                drive.setting("manualS", .3);
            } else {
                drive.setting("manualL", 0);
                drive.setting("manualR", 0);
                drive.setting("manualS", 0);
            }

            if (gamepadExtended1.dpad_up.equals(GamepadExtended.ButtonState.DOWNING)) {
                servoLoc += .1;
                pawl.setPosition(servoLoc);
            } else if (gamepadExtended1.dpad_down.equals(GamepadExtended.ButtonState.DOWNING)) {
                servoLoc -= .1;
                pawl.setPosition(servoLoc);
            } else if (gamepadExtended1.dpad_left.equals(GamepadExtended.ButtonState.DOWNING)) {
                servoLoc -= .01;
                pawl.setPosition(servoLoc);
            } else if (gamepadExtended1.dpad_right.equals(GamepadExtended.ButtonState.DOWNING)) {
                servoLoc += .01;
                pawl.setPosition(servoLoc);
            }

            telemetry.addData("gyro heading", gyro.getLastNValues(1)[0]);
            telemetry.addData("servo", servoLoc);
        }
    }
}
