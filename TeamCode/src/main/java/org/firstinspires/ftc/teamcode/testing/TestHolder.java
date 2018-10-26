package org.firstinspires.ftc.teamcode.testing;

import org.firstinspires.ftc.teamcode.core.ClassHolder;
import org.firstinspires.ftc.teamcode.core.OpModeExtended;
import org.firstinspires.ftc.teamcode.core.SensorManager;
import org.firstinspires.ftc.teamcode.core.Subsystem;

import org.majora320.tealisp.evaluator.JavaInterface;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    @Override
    public Set<JavaInterface> getInterfaces() {
        return new HashSet<>();
    }
}
