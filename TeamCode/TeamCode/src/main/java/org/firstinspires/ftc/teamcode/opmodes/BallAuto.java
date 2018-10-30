package org.firstinspires.ftc.teamcode.opmodes;

import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.balldrive.DriveSubsystem;
import org.firstinspires.ftc.teamcode.core.ClassHolder;
import org.firstinspires.ftc.teamcode.core.OpModeExtended;
import org.firstinspires.ftc.teamcode.core.Registry;
import org.firstinspires.ftc.teamcode.core.Subsystem;
import org.majora320.tealisp.evaluator.LispObject;

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
            teaLispFile = new File("./storage/emulated/0/bluetooth/ballauto.tl");
            drive = (DriveSubsystem) Registry.getSubsystemByName("driveSubsystem");
            drive.setting("mode", DriveSubsystem.Mode.AUTO_STOP);
            ((MainInterface) Registry.getInterfaceByName("main")).setDrive(drive);
        }

        @Override
        public void autoupdate() {

        }
    }
}
