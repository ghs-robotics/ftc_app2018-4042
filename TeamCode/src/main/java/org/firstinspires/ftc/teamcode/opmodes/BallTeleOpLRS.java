package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.InputControlManager;
import org.firstinspires.ftc.teamcode.control.TeleOpInputManager;

@TeleOp(name = "bla Teleop lrs", group = "tele")
public class BallTeleOpLRS extends BaseTeleOp {

    @Override
    public void init() {
        super.init();
        InputControlManager.USE_TIMER = false;
        TeleOpInputManager.DRIVE_XYR = false;
    }
}
