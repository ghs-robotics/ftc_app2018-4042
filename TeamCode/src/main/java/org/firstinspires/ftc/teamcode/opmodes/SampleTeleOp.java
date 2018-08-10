package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.InputControlManager;
import org.firstinspires.ftc.teamcode.control.Statics;
import org.firstinspires.ftc.teamcode.io.output.Log;
import org.firstinspires.ftc.teamcode.io.output.LoggerThread;

@TeleOp(name = "Sample Teleop", group = "tele")
public class SampleTeleOp extends OpMode {

    private InputControlManager input;

    @Override
    public void init() {
        Log.self = null;
        Statics.set(telemetry, gamepad1, gamepad2, hardwareMap);
        input = InputControlManager.get();
    }

    @Override
    public void loop() {
        input.update();
    }

    @Override
    public void stop() {
        LoggerThread.RUN = false;
    }
}
