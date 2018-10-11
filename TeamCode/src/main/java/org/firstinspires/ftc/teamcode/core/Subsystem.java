package org.firstinspires.ftc.teamcode.core;

import java.util.HashMap;
import java.util.Map;

public abstract class Subsystem {
    public Map<String, Object> settings = new HashMap<>();

    public abstract void init();
    public abstract void updateData();
    public abstract void updateActuators();
    public void setting(String name, Object object) {
        settings.put(name, object);
    }
    public Object getObjectSetting(String name) {
        return settings.get(name);
    }
}
