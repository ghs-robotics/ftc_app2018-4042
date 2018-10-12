package org.firstinspires.ftc.teamcode.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class Subsystem {
    public OpModeExtended context;
    public Map<String, Field> settings = new HashMap<>();

    public Subsystem(OpModeExtended context) {
        this.context = context;
    }

    public abstract void init();
    public abstract void updateData();
    public abstract void updateActuators();


    public void registerSettings() {
        for (Field field : this.getClass().getDeclaredFields()) {
            String name = field.getName();
            Annotation[] annotations = field.getDeclaredAnnotations();
            context.telemetry.log().add("annotations registered:");
            for (Annotation annotation : annotations) {
                if (annotation instanceof Setting) {
                    settings.put(name, field);
                    context.telemetry.log().add(annotation + "");
                }
            }
        }
    }

    public void setting(String name, Object object) {
        try {
            settings.get(name).set(this, object);
        } catch (IllegalAccessException e) {}
    }
    public Object getSetting(String name) {
        try {
            return settings.get(name).get(this);
        } catch (IllegalAccessException e) {
            return "oops";
        }
    }
}
