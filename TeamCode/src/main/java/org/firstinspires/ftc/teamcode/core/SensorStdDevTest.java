package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;

import java.util.ArrayList;

@TeleOp(name="SensorStdDevTest", group="Iterative Opmode")
// This opmode gets the standard deviation of a sensor by
// constantly replacing voltages in a list consisting of 100 values
public class SensorStdDevTest extends OpMode {
    static final int NUM_VOLTAGES = 100;
    AnalogInput ultrasonic;
    ArrayList<Double> voltageList;
    double sum;
    double mean;
    double stdDev;

     @Override
     public void init() {
         ultrasonic = hardwareMap.analogInput.get("AARON\'S A BITCH");
         voltageList.ensureCapacity(NUM_VOLTAGES);
     }
     @Override
     public void loop() {
         sum = 0;
         voltageList.add(ultrasonic.getVoltage());
         if(voltageList.size() > NUM_VOLTAGES)
             voltageList.remove(0);

         for(double voltage : voltageList){
             sum += voltage;
         }
         mean = sum / voltageList.size();
         for(double voltage : voltageList){
             sum += Math.pow((voltage - mean), 2);
         }
         stdDev = Math.sqrt(sum/(voltageList.size()-1));
         telemetry.addData("hi brendan", stdDev);
         telemetry.update();
     }
}


