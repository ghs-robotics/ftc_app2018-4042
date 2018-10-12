package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.core.ClassHolder;
import org.firstinspires.ftc.teamcode.core.OpModeExtended;
import org.firstinspires.ftc.teamcode.core.Registry;
import org.firstinspires.ftc.teamcode.core.Subsystem;

@TeleOp(name = "bla Teleop xyr", group = "tele")
public class BallTeleOpXYR extends OpModeExtended {

    public InputControlManager getInputControlManager() {
        return new BallTeleOpXYR.TICM();
    }

    public ClassHolder getClassHolder() {
        return new Holder(this);
    }

    public class TICM extends OpModeExtended.TeleInputControlManager {
        public void teleinit() {  }
        public void teleupdate() {
            Subsystem subsystem = Registry.getSubsystemByName("driveSubsystem");
            subsystem.setting("xyr", true);
        }
    }
}