package org.firstinspires.ftc.teamcode.core;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;

@TeleOp(name="Otto and Aarons mawm package bithc fuck" +
        " you i hate u so much read this bs lick", group="Iterative Opmode")
public class OttoandAaronsMawm extends OpMode {
        AnalogInput infrared;

        @Override
        public void init() {
            infrared = hardwareMap.analogInput.get("whisker");
        }
        @Override
        public void loop() {
            telemetry.addData("hi brendan", infrared.getVoltage());
            telemetry.update();
        }

    }

