package org.firstinspires.ftc.teamcode.balldrive;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.core.OpModeExtended;
import org.firstinspires.ftc.teamcode.core.Setting;
import org.firstinspires.ftc.teamcode.core.Subsystem;
import org.firstinspires.ftc.teamcode.core.path.PathData;

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

    private double finalL, finalR, finalS;

    public enum Mode {MANUAL_XYR, MANUAL_LRS, AUTO_LRS_INIT, AUTO_LRS, AUTO_LRS_STOP, AUTO_PATH, AUTO_IDLE}

    @Setting
    public Mode mode;

    @Setting
    public ElapsedTime timer;

    @Setting
    public PathData path;

    @Setting
    public double startTime;

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
}
