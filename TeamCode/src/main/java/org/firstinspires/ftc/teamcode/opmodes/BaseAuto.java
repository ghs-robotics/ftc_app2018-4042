package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.control.AutoInputManager;
import org.firstinspires.ftc.teamcode.control.AutoOperations;
import org.firstinspires.ftc.teamcode.control.InputControlManager;
import org.firstinspires.ftc.teamcode.control.Operations;
import org.firstinspires.ftc.teamcode.control.Statics;
import org.firstinspires.ftc.teamcode.control.TeleOpInputManager;
import org.firstinspires.ftc.teamcode.io.output.Log;
import org.firstinspires.ftc.teamcode.io.output.LoggerThread;

@Autonomous(name = "Simple Auto", group = "auto")
@Disabled
public class BaseAuto extends OpMode {

    private InputControlManager input;

    @Override
    public void init() {
        InputControlManager.self = null;
        TeleOpInputManager.self = null;
        Operations.self = null;
        Log.self = null;
        AutoInputManager.self = null;
        AutoOperations.self = null;
        Statics.self = null;
        Statics.set(telemetry, gamepad1, gamepad2, hardwareMap);
        input = InputControlManager.get("example.txt"); //Should be in implementing classes not here
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
