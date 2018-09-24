package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.core.highlevel.InputControlManager;
import org.firstinspires.ftc.teamcode.core.Statics;
import org.firstinspires.ftc.teamcode.core.io.output.Log;
import org.firstinspires.ftc.teamcode.core.io.output.LoggerThread;

@TeleOp(name = "Simple Teleop", group = "tele")
public class BaseTeleOp extends OpMode {

    private InputControlManager input;

    @Override
    public void init() {
        InputControlManager.self = null;
        Log.self = null;
        Statics.self = null;

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
