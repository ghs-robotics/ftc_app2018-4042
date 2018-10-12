package org.firstinspires.ftc.teamcode.balldrive;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.OpModeExtended;

public class DriveInterface {

    private OpModeExtended context;

    private DcMotor motorL, motorR, motorS;

    public DriveInterface(OpModeExtended context) {
        motorL = context.hardwareMap.dcMotor.get("motorLF");
        motorR = context.hardwareMap.dcMotor.get("motorRF");
        motorS = context.hardwareMap.dcMotor.get("motorStrafe");
    }

    public void drive(double l, double r, double s) {

    }
}
