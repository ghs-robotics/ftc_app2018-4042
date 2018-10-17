package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.io.input.GamepadAdv;

@TeleOp(name = "ButtonTest2", group = "Iterative Opmode")
@Disabled
public class ButtonTest2 extends OpMode {
    boolean Var = true;

    GamepadAdv gamepad;

    public void init() {
        gamepad = new GamepadAdv(gamepad1);
    }

    public void loop() {
        gamepad.update();
        if(gamepad.aUp()){
            telemetry.addData("ur mamaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", gamepad.aUp());
        }
        //telemetry.addData("adown",gamepad.aDown());


        //telemetry.addData("aup",gamepad.aUp());

    }

}
