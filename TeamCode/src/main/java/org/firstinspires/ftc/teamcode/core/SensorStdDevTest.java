package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;

import java.util.ArrayList;

@TeleOp(name="SensorStdDevTest", group="Iterative Opmode")
public class SensorStdDevTest extends OpMode {
    static final int NUM_VOLTAGES = 100;

    AnalogInput ultrasonic;
    ArrayList<Double> voltageList;
    double sum;
    double mean;
    double stdDev;

     @Override
     public void init() {
         voltageList = new ArrayList<Double>();
         ultrasonic = hardwareMap.analogInput.get("AARON\'S A BITCH");
         voltageList.ensureCapacity(NUM_VOLTAGES);
     }
     @Override
     public void loop() {
         sum = 0;
         double currentVoltage = ultrasonic.getVoltage();
         if(voltageList.size() >= NUM_VOLTAGES){
             voltageList.remove(0);
         }
         voltageList.add(currentVoltage);
         for(double voltage : voltageList){
             sum += voltage;
         }
         mean = sum / voltageList.size();
         sum = 0;
         for(double voltage : voltageList){
             sum += Math.pow((voltage - mean), 2);
         }
         stdDev = Math.sqrt(sum/(voltageList.size()-1));
         telemetry.addData("stdDev", stdDev);
         telemetry.addData("Voltage", currentVoltage);
         telemetry.addData("Length", voltageList.size());
         telemetry.addData("Mean", mean);
         telemetry.update();
     }
}


