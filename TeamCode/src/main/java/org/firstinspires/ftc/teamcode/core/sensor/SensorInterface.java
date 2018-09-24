package org.firstinspires.ftc.teamcode.core.sensor;

public abstract class SensorInterface {

    String mapName;

    public abstract void update();

    public abstract double getValue();
}
