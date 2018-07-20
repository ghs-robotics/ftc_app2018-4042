package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.io.input.AutoFileReader;
import org.firstinspires.ftc.teamcode.io.input.GamepadAdv;

@TeleOp(name = "Plausecause", group = "Iterative Opmode")
public class FileLogger2 extends OpMode {
    AutoFileReader mama;
    GamepadAdv fafa;

    public void init() {
        fafa = new GamepadAdv(gamepad1);
        mama = AutoFileReader.get();
        mama.setFile("hello.txt");
        telemetry.addData("test", mama.advanceOp());

    }

    public void loop() {
        fafa.update();

        if (fafa.aDown()) {
            telemetry.addData("kaka", fafa.aDown());

            if (mama.advanceOp() == null || mama.getCurrOp().equals("")) {
                telemetry.addData("yo mama done eating now", "!");
            } else {
                telemetry.addData("Your mama eats:", mama.advanceOp());

            }
        }
    }
}



