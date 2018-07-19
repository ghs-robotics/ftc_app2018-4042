package org.firstinspires.ftc.teamcode.io.input;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "ButtonTest2", group = "Iterative Opmode")
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
