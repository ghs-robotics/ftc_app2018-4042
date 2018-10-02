package org.firstinspires.ftc.teamcode.testing;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.core.OpModeExtended;
import org.firstinspires.ftc.teamcode.core.Subsystem;

import java.util.Map;

public class FakeLoggingSubsystem extends Subsystem {
    OpModeExtended context;
    Telemetry telemetry;

    public FakeLoggingSubsystem(OpModeExtended context) {
        this.context = context;
        this.telemetry = context.telemetry;
    }

    public void init() {

    }

    public void updateData() {

    }

    public void updateActuators() {
        for (Map.Entry<String, Object> entry : settings.entrySet()) {
            telemetry.clearAll();
            if (entry.getKey().startsWith("log: ")) {
                telemetry.log().add(entry.getKey().substring(5)
                        + ": " + entry.getValue());
            }
            telemetry.update();
        }
    }
}
