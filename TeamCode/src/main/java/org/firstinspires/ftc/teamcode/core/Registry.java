package org.firstinspires.ftc.teamcode.core;

import org.firstinspires.ftc.teamcode.core.sensor.SensorManager;
import org.firstinspires.ftc.teamcode.core.subsystem.Subsystem;
import org.firstinspires.ftc.teamcode.core.subsystem.SubsystemTint;

import java.util.ArrayList;

public class Registry {
    public static ArrayList<Subsystem> subsystems;
    public static ArrayList<SensorManager> sensors;
    public static ArrayList<SubsystemTint> subsystemTints;

    public static void updateSensors() {
        for (SensorManager s: sensors) {
            s.update();
        }
    }

    public static void updateData() {
        for (Subsystem s: subsystems) {
            s.updateData();
        }
    }

    public static void updateActuators() {
        for (Subsystem s: subsystems) {
            s.updateActuators();
        }
    }

    public static void initSubsystems() {
        for(Subsystem s: subsystems) {
            s.init();
        }
    }

    public static void addSensor(SensorManager s) {
        sensors.add(s);
    }

    public static void initSensors() {
        for(SensorManager s: sensors) {
            s.init();
        }
    }
}
