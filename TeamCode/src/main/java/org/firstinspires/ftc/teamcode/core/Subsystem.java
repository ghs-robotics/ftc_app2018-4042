package org.firstinspires.ftc.teamcode.core;

import java.util.HashMap;
import java.util.Map;

public abstract class Subsystem {
    public Map<String, Integer> integers = new HashMap<>();
    public Map<String, Double> doubles = new HashMap<>();
    public Map<String, Boolean> booleans = new HashMap<>();
    public Map<String, String> strings = new HashMap<>();

    public abstract void init();
    public abstract void updateData();
    public abstract void updateActuators();
    public void setting(String name, int value) {
        integers.put(name, value);
    }
    public void setting(String name, double value) {
        doubles.put(name, value);
    }
    public void setting(String name, boolean value) {
        booleans.put(name, value);
    }
    public void setting(String name, String value) {
        strings.put(name, value);
    }
    public int getIntegerSetting(String name) {
        return integers.get(name);
    }
    public double getDoubleSetting(String name) {
        return doubles.get(name);
    }
    public boolean getBooleanSetting(String name) {
        return booleans.get(name);
    }
    public String getStringSetting(String name) {
        return strings.get(name);
    }
}
