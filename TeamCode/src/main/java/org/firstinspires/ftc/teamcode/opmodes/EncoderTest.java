package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.balldrive.DriveSubsystem;
import org.firstinspires.ftc.teamcode.core.structure.ClassHolder;
import org.firstinspires.ftc.teamcode.core.iobuiltin.GamepadExtended;
import org.firstinspires.ftc.teamcode.core.OpModeExtended;
import org.firstinspires.ftc.teamcode.core.structure.Registry;
import org.firstinspires.ftc.teamcode.core.structure.Subsystem;

@TeleOp(name = "Encoder Test", group = "tele")
public class EncoderTest extends OpModeExtended {

    public OpModeExtended.InputControlManager getInputControlManager() {
        return new EncoderTest.TICM();
    }

    public ClassHolder getClassHolder() {
        return new Holder(this);
    }

    public class TICM extends OpModeExtended.TeleInputControlManager {
        Subsystem drive;
        public void teleinit() {
            drive = Registry.getSubsystemByName("driveSubsystem");
            drive.setting("mode", DriveSubsystem.Mode.MANUAL_LRS);
            drive.setting("encoderPrint", true);
        }
        public void teleupdate() {
            drive.setting("manualL", .3);
            drive.setting("manualR", .3);
            drive.setting("manualS", .3);
        }
    }
}
