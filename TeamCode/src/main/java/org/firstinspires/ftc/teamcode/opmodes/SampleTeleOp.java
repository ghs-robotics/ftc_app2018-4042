package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.InputControlManager;

@TeleOp(name = "Sample Teleop", group = "tele")
@Disabled
public class SampleTeleOp extends OpMode {

    private InputControlManager input;

    @Override
    public void init() {
        input = InputControlManager.get();
    }

    @Override
    public void loop() {
        input.update();
    }
}
