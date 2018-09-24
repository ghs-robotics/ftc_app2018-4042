package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.core.Registry;
import org.firstinspires.ftc.teamcode.core.highlevel.InputControlManager;
import org.firstinspires.ftc.teamcode.core.Statics;
import org.firstinspires.ftc.teamcode.core.io.input.IOUtils;
import org.firstinspires.ftc.teamcode.core.io.output.Log;
import org.firstinspires.ftc.teamcode.core.io.output.LoggerThread;

import java.io.File;

import teascript.Nice;
import teascript.Tint;

import static teascript.Nice.addTint;

@Autonomous(name = "Sample Auto", group = "auto")
@Disabled
public class BaseAuto extends OpMode {

    private InputControlManager input;

    @Override
    public void init() {
        InputControlManager.self = null;
        Log.self = null;
        Statics.self = null;

        Statics.set(telemetry, gamepad1, gamepad2, hardwareMap);
        input = InputControlManager.get(true); //Should be in implementing classes not here

        for (Tint subsystemTint : Registry.subsystemTints) {
            addTint(subsystemTint);
        }

        Nice.init(new File(IOUtils.FILE_ROOT, "example.txt"));
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
