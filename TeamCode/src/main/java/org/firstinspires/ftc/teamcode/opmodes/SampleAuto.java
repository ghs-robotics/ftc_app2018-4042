package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.control.InputControlManager;
import org.firstinspires.ftc.teamcode.control.Statics;

@Autonomous(name = "Sample Auto", group = "auto")
@Disabled
public class SampleAuto extends OpMode {

    private InputControlManager input;

    @Override
    public void init() {
        input = InputControlManager.get("example.txt");
        Statics.set(telemetry, gamepad1, gamepad2, hardwareMap);
    }

    @Override
    public void loop() {
        input.update();
    }
}
