package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.balldrive.DriveSubsystem;
import org.firstinspires.ftc.teamcode.core.structure.ClassHolder;
import org.firstinspires.ftc.teamcode.core.OpModeExtended;
import org.firstinspires.ftc.teamcode.core.structure.Registry;

import java.io.File;

@Autonomous(name = "bla Auto", group = "tele")
public class BallAuto extends OpModeExtended {

    public OpModeExtended.InputControlManager getInputControlManager() {
        return new BallAuto.AICM();
    }

    public ClassHolder getClassHolder() {
        return new Holder(this);
    }

    public class AICM extends OpModeExtended.AutoInputControlManager {
        DriveSubsystem drive;

        @Override
        public void autoinit() {
            teaLispFile = new File("./storage/emulated/0/bluetooth/one-motion.tl");
            drive = (DriveSubsystem) Registry.getSubsystemByName("driveSubsystem");
            drive.setting("mode", DriveSubsystem.Mode.AUTO_IDLE);
            ((MainInterface) Registry.getInterfaceByName("main")).setDrive(drive);
        }

        @Override
        public void autoupdate() {

        }
    }
}
