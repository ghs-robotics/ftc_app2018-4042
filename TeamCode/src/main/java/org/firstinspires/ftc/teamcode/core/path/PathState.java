package org.firstinspires.ftc.teamcode.core.path;

public class PathState {
    public double time;
    public double pos;
    public double vel;
    public double acc;

    PathState(double time, double pos, double vel, double acc) {
        this.time = time;
        this.pos = pos;
        this.vel = vel;
        this.acc = acc;
    }
}