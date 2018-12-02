package org.firstinspires.ftc.teamcode.balldrive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.core.OpModeExtended;

public class LiftActuator {

    private DcMotorEx lift;
    private Servo pawl;

    private OpModeExtended context;

    //TODO: TUNE THESE
    private static final double OPEN = 0;
    private static final double CLOSED = 1;

    public LiftActuator(OpModeExtended context) {
        this.context = context;
    }

    public void init() {
        lift = (DcMotorEx) context.hardwareMap.dcMotor.get("lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setDirection(DcMotorSimple.Direction.FORWARD);

        pawl = context.hardwareMap.servo.get("pawl");
        pawl.setDirection(Servo.Direction.FORWARD);
        pawl.setPosition(CLOSED);
    }

    public void open() {
        setPawl(OPEN);
    }

    public void closed() {
        setPawl(CLOSED);
    }

    public void setLift(double power) {
        lift.setPower(power);
    }

    public void setPawl(double pos) {
        pawl.setPosition(pos);
    }
}
