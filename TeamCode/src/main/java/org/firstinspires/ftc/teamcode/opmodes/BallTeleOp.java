package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.InputControlManager;

@TeleOp(name = "bla Teleop", group = "tele")
public class BallTeleOp extends BaseTeleOp {

    private InputControlManager input;

    @Override
    public void init() {
        super.init();
        InputControlManager.USE_TIMER = false;
    }
}
