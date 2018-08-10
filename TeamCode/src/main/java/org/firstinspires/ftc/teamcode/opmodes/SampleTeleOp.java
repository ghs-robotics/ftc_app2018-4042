package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.InputControlManager;
import org.firstinspires.ftc.teamcode.control.Statics;
import org.firstinspires.ftc.teamcode.io.output.Log;

@TeleOp(name = "Sample Teleop", group = "tele")
public class SampleTeleOp extends OpMode {

    private InputControlManager input;

    private int count = 0;

    @Override
    public void init() {
        Statics.set(telemetry, gamepad1, gamepad2);
        input = InputControlManager.get();
    }

    @Override
    public void loop() {
        input.update();
        count++;
        if (count > 2000) {
            Log.stop();
        }
    }

    @Override
    public void stop() {
        Log.stop();
    }
}
