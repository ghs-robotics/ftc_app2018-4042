package org.firstinspires.ftc.teamcode.balldrive;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.core.OpModeExtended;

public class DriveActuator {

    private DcMotorEx motorL, motorR, motorS;

    private OpModeExtended context;

    public DriveActuator(OpModeExtended context) {
        this.context = context;
        init();
    }

    public void init() {
        motorL = (DcMotorEx) context.hardwareMap.dcMotor.get("motorLF");
        motorR = (DcMotorEx) context.hardwareMap.dcMotor.get("motorRF");
        motorS = (DcMotorEx) context.hardwareMap.dcMotor.get("motorStrafe");

        motorL.setDirection(DcMotorSimple.Direction.REVERSE);
        motorR.setDirection(DcMotorSimple.Direction.FORWARD);
        motorS.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setPower(double l, double r, double s) {
        motorL.setPower(l);
        motorR.setPower(r);
        motorS.setPower(s);
    }

    public void setVelocity(double l, double r, double s) {
        motorL.setVelocity(l, AngleUnit.DEGREES);
        motorR.setVelocity(r, AngleUnit.DEGREES);
        motorS.setVelocity(s, AngleUnit.DEGREES);
    }
}
