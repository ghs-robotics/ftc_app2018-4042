package org.firstinspires.ftc.teamcode.core;

import java.util.Map;

public abstract class ClassHolder {
    public OpModeExtended context;

    public ClassHolder(OpModeExtended context) {
        this.context = context;
    }

    public abstract Map<String, Subsystem> getSubsystems();
    public abstract Map<String, SensorManager> getSensors();
    // TODO: Set<ThingThatAllowsCallingJavaFunctionsFromTeaSystem>
}
