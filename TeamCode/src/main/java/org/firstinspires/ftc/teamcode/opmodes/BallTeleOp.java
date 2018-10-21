package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.balldrive.DriveSubsystem;
import org.firstinspires.ftc.teamcode.core.ClassHolder;
import org.firstinspires.ftc.teamcode.core.GamepadExtended;
import org.firstinspires.ftc.teamcode.core.OpModeExtended;
import org.firstinspires.ftc.teamcode.core.Registry;
import org.firstinspires.ftc.teamcode.core.Subsystem;

@TeleOp(name = "bla Teleop", group = "tele")
public class BallTeleOp extends OpModeExtended {

    public OpModeExtended.InputControlManager getInputControlManager() {
        return new BallTeleOp.TICM();
    }

    public ClassHolder getClassHolder() {
        return new Holder(this);
    }

    public class TICM extends OpModeExtended.TeleInputControlManager {
        Subsystem drive;
        public void teleinit() {
            drive = Registry.getSubsystemByName("driveSubsystem");
            drive.setting("mode", DriveSubsystem.Mode.MANUAL_LRS);
        }
        public void teleupdate() {
            if (gamepadExtended1.a == GamepadExtended.ButtonState.DOWNING) {
                DriveSubsystem.Mode mode = (DriveSubsystem.Mode) drive.getSetting("mode");
                switch (mode) {
                    case MANUAL_XYR:
                        drive.setting("mode", DriveSubsystem.Mode.MANUAL_LRS);
                        break;
                    case MANUAL_LRS:
                        drive.setting("mode", DriveSubsystem.Mode.MANUAL_XYR);
                        break;
                }
            }

            switch ((DriveSubsystem.Mode) drive.getSetting("mode")) {
                case MANUAL_LRS:
                    drive.setting("l", -gamepadExtended1.left_stick_y);
                    drive.setting("r", -gamepadExtended1.right_stick_y);
                    drive.setting("s", (gamepadExtended1.left_stick_x + gamepadExtended1.right_stick_x) / 2);
                    break;
                case MANUAL_XYR:
                    drive.setting("l", gamepadExtended1.right_stick_x - gamepadExtended1.left_stick_y);
                    drive.setting("r", gamepadExtended1.right_stick_x + gamepadExtended1.right_stick_y);
                    drive.setting("s", gamepadExtended1.left_stick_x);
            }
        }
    }
}
