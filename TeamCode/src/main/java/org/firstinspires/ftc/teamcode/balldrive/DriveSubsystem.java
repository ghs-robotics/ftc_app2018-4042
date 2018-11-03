package org.firstinspires.ftc.teamcode.balldrive;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.core.OpModeExtended;
import org.firstinspires.ftc.teamcode.core.Setting;
import org.firstinspires.ftc.teamcode.core.Subsystem;
import org.firstinspires.ftc.teamcode.core.path.PathData;
import org.firstinspires.ftc.teamcode.core.path.PathFactory;
import org.firstinspires.ftc.teamcode.core.path.PathState;

public class DriveSubsystem extends Subsystem {
    private OpModeExtended context;

    private DriveActuator actuator;

    @Setting
    public double manualL;
    @Setting
    public double manualR;
    @Setting
    public double manualS;
    @Setting
    public double manualX;
    @Setting
    public double manualY;

    @Setting
    public double autoL;
    @Setting
    public double autoR;
    @Setting
    public double autoS;
    @Setting
    public double autoT;

    @Setting
    public double initPos;
    @Setting
    public double initVel;
    @Setting
    public double finalPos;
    @Setting
    public double finalVel;
    @Setting
    public double maxVel;
    @Setting
    public double maxAccel;
    @Setting
    public double timestep;

    private double finalL, finalR, finalS;

    public enum Mode {
        MANUAL_XYR, MANUAL_LRS,
        AUTO_LRS_INIT, AUTO_LRS, AUTO_LRS_STOP,
        AUTO_PATH_INIT, AUTO_PATH, AUTO_PATH_STOP,
        AUTO_IDLE}

    @Setting
    public Mode mode;

    @Setting
    public ElapsedTime timer;

    @Setting
    public PathData path;

    private double startTime;

    public DriveSubsystem(OpModeExtended context) {
        super(context);
        this.context = context;
        this.actuator = new DriveActuator(context);
    }

    public void init() {
        manualL = 0;
        manualR = 0;
        manualS = 0;
        manualX = 0;
        manualY = 0;
        finalL = 0;
        finalR = 0;
        finalS = 0;

        timer = new ElapsedTime();
    }

    public void updateData() {
        switch (mode) {
            case MANUAL_LRS:
                finalL = manualL;
                finalR = manualR;
                finalS = manualS;
                break;
            case MANUAL_XYR:
                finalL = manualY + manualR;
                finalR = manualY - manualR;
                finalS = manualX;
                break;
            case AUTO_LRS_INIT:
                timer.reset();
                mode = Mode.AUTO_LRS;
                break;
            case AUTO_LRS:
                if (timer.seconds() <= autoT) {
                    finalL = autoL;
                    finalR = autoR;
                    finalS = autoS;
                } else {
                    mode = Mode.AUTO_LRS_STOP;
                }
                break;
            case AUTO_LRS_STOP:
                finalL = 0;
                finalR = 0;
                finalS = 0;
                mode = Mode.AUTO_IDLE;
                break;
            case AUTO_PATH_INIT:
                startTime = currTime();
                PathFactory factory = new PathFactory(initPos, initVel, finalPos, finalVel, maxVel, maxAccel, timestep);
                path = factory.data;
                mode = Mode.AUTO_PATH;
                break;
            case AUTO_PATH:
                PathState nextState = path.getForTime(currTime() - startTime);
                if (nextState.equals(PathState.END_POINT)) {
                    mode = Mode.AUTO_PATH_STOP;
                } else {
                    finalS = nextState.vel;
                }
                break;
            case AUTO_PATH_STOP:
                finalS = 0;
                break;
        }
        context.telemetry.addData("aaa","bbb");
    }

    public void updateActuators() {
        context.telemetry.addData("l", finalL);
        context.telemetry.addData("r", finalR);
        context.telemetry.addData("s", finalS);
        context.telemetry.addData("mode", mode.name());

        if (mode.equals(Mode.AUTO_PATH)) {
            actuator.setVelocity(finalL, finalR, finalS);
        } else {
            actuator.setPower(finalL, finalR, finalS);
        }
    }

    private double currTime() {
        return System.currentTimeMillis() / 1000.0; //convert to seconds
    }
}
