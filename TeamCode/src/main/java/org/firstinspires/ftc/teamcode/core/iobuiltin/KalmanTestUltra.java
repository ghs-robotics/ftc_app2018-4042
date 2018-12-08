package org.firstinspires.ftc.teamcode.core.iobuiltin;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

import org.firstinspires.ftc.robotcore.external.navigation.Axis;
import org.firstinspires.ftc.teamcode.core.OpModeExtended;
import org.firstinspires.ftc.teamcode.core.structure.SensorInterface;

import java.util.ArrayList;
@TeleOp(name="KalmanTest", group="Iterative Opmode")
public class KalmanTestUltra implements SensorInterface {
    AnalogInput ultrasonic;
    double voltage;
    HardwareMap hardwareMap;

    public KalmanTestUltra(OpModeExtended context) {
        this.hardwareMap = context.hardwareMap;
    }
    @Override
    public void init() {
        ultrasonic = hardwareMap.analogInput.get("ultrasonic");
    }

    @Override
    public double getCMValue() {
        return voltage;
    }

    @Override
    public void update() {
        voltage = ultrasonic.getVoltage();
    }

    @Override
    public double getRawValue() {
        return voltage;
    }

    public String getConfigName(){
        return "ultrasonic";
    }



}
