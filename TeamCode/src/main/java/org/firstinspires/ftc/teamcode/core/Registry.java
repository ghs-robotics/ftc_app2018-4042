package org.firstinspires.ftc.teamcode.core;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Registry {
    public static Map<String, SensorManager> sensors = new HashMap<>();
    public static Map<String, Subsystem> subsystems = new HashMap<>();

    public static void grabData(ClassHolder classHolder) {
        if (classHolder == null) {
            Log.i("team-code", "classHolder null: sensors and subsystems made null");
            sensors = null;
            subsystems = null;
            return;
        }
        sensors = classHolder.getSensors();
        subsystems = classHolder.getSubsystems();
    }

    public static void addSensorManager(String name, SensorManager sensor) {
        Log.d("team-code", "adding SensorManager " + name
                + (sensor == null ? " (null)" : " (nonnull)"));
        if (sensors == null)
            Log.w("team-code", "sensors null, but attempt made to add SensorManager");
        sensors.put(name, sensor);
    }

    public static void addSubsystem(String name, Subsystem subsystem) {
        Log.d("team-code", "adding Subsystem " + name
                + (subsystem == null ? " (null)": " (nonnull)"));
        if (subsystems == null)
            Log.w("team-code", "subsystems null, but attempt made to add Subsystem");
        subsystems.put(name, subsystem);
    }

    public static SensorManager getSensorManagerByName(String name) {
        if (sensors == null) {
            Log.w("team-code", "attempt to fetch SensorManager " + name
                    + " but sensors is null");
        }
        SensorManager result =  sensors.get(name);
        if (result == null)
            Log.w("team-code", "get by name: could not find SensorManager named " + name);
        return result;
    }

    public static Subsystem getSubsystemByName(String name) {
        if (subsystems == null) {
            Log.w("team-code", "attempt to fetch Subsystem " + name
                    + " but subsystems is null");
        }
        Subsystem result = subsystems.get(name);
        if (result == null)
            Log.w("team-code", "get by name: could not find Subsystem named " + name);
        return result;
    }

    public static void initSensors() {
        if (sensors == null)
            return;
        for (SensorManager sensor : sensors.values()) {
            sensor.init();
        }
    }

    public static void updateSensors() {
        if (sensors == null)
            return;
        for (SensorManager sensor : sensors.values()) {
            sensor.update();
        }
    }

    public static void initSubsystems() {
        if (subsystems == null)
            return;
        for (Subsystem subsystem : subsystems.values()) {
            subsystem.registerSettings();
            subsystem.init();
        }
    }

    public static void updateSubsystemData() {
        if (subsystems == null)
            return;
        for (Subsystem subsystem : subsystems.values()) {
            subsystem.updateData();
        }
    }

    public static void updateSubsystemActuators() {
        if (subsystems == null)
            return;
        for (Subsystem subsystem : subsystems.values()) {
            subsystem.updateActuators();
        }
    }
}
