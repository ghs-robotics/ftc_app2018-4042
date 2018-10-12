package org.firstinspires.ftc.teamcode.testing;

import org.firstinspires.ftc.teamcode.core.ClassHolder;
import org.firstinspires.ftc.teamcode.core.OpModeExtended;
import org.firstinspires.ftc.teamcode.core.SensorManager;
import org.firstinspires.ftc.teamcode.core.Subsystem;

import java.util.HashMap;
import java.util.Map;

public class TestHolder extends ClassHolder {

    public TestHolder(OpModeExtended context) {
        super(context);
    }

    public Map<String, Subsystem> getSubsystems() {
        Map<String, Subsystem> result = new HashMap<>();

        result.put("fakeLoggingSubsystem", new FakeLoggingSubsystem(context));

        return result;
    }

    public Map<String, SensorManager> getSensors() {
        Map<String, SensorManager> result = new HashMap<>();

        result.put("fakeTimeSensor",
                new SensorManager(
                context, new FakeTimerSensor(context.hardwareMap, "")));

        return result;
    }

    // TODO: Set<ThingThatAllowsCallingJavaFunctionsFromTeaSystem>
}
