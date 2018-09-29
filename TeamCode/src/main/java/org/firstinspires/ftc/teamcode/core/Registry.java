package org.firstinspires.ftc.teamcode.core;

import java.util.HashMap;
import java.util.Map;

public class Registry {
    public static Map<String, SensorManager> sensors = new HashMap<>();
    public static Map<String, Subsystem> subsystems = new HashMap<>();

    public static void reset() {
        sensors = new HashMap<>();
        subsystems = new HashMap<>();
    }

    public static void grabData(ClassHolder classHolder) {
        sensors = classHolder.getSensors();
        subsystems = classHolder.getSubsystems();
    }

    public static void addSensorManager(String name, SensorManager sensor) {
        sensors.put(name, sensor);
    }

    public static void addSubsystem(String name, Subsystem subsystem) {
        subsystems.put(name, subsystem);
    }

    public static SensorManager getSensorManagerByName(String name) {
        return sensors.get(name);
    }

    public static Subsystem getSubsystemByName(String name) {
        return subsystems.get(name);
    }

    public static void initSensors() {
        for (SensorManager sensor : sensors.values()) {
            sensor.init();
        }
    }

    public static void updateSensors() {
        for (SensorManager sensor : sensors.values()) {
            sensor.update();
        }
    }

    public static void initSubsystems() {
        for (Subsystem subsystem : subsystems.values()) {
            subsystem.init();
        }
    }

    public static void updateSubsystemData() {
        for (Subsystem subsystem : subsystems.values()) {
            subsystem.updateData();
        }
    }

    public static void updateSubsystemActuators() {
        for (Subsystem subsystem : subsystems.values()) {
            subsystem.updateActuators();
        }
    }
}
