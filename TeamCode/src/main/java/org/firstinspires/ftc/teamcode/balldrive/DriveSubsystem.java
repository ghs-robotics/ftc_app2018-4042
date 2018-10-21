package org.firstinspires.ftc.teamcode.balldrive;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.OpModeExtended;
import org.firstinspires.ftc.teamcode.core.Setting;
import org.firstinspires.ftc.teamcode.core.Subsystem;

public class DriveSubsystem extends Subsystem {
    private OpModeExtended context;

    @Setting
    public double l;
    @Setting
    public double r;
    @Setting
    public double s;

    private DcMotor motorL, motorR, motorS;

    public enum Mode {MANUAL_XYR, MANUAL_LRS}

    @Setting
    public Mode mode;

    public DriveSubsystem(OpModeExtended context) {
        super(context);
        this.context = context;
    }

    public void init() {
        l = 0;
        r = 0;
        s = 0;

        motorL = context.hardwareMap.dcMotor.get("motorLF");
        motorR = context.hardwareMap.dcMotor.get("motorRF");
        motorS = context.hardwareMap.dcMotor.get("motorStrafe");
    }

    public void updateData() {

    }

    public void updateActuators() {
        motorL.setPower(l);
        motorR.setPower(r);
        motorS.setPower(s);

        context.telemetry.addData("l", l);
        context.telemetry.addData("r", r);
        context.telemetry.addData("s", s);
    }
}
