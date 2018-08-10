package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Bren on 8/10/2018.
 */

public class Drive {
    private static Drive self = null;
    private DcMotor mLeftFront = null;
    private DcMotor mLeftBack = null;
    private DcMotor mRightFront = null;
    private DcMotor mRightBack = null;
    private DcMotor mStrafe = null;

    private Drive(){}

    public static Drive get() {
        if (self == null) {
            self = new Drive();
        }
        self.mLeftFront = Statics.hardwareMap().dcMotor.get("motorLF");
        //self.mLeftBack = Statics.hardwareMap().dcMotor.get("motorLB");
        self.mRightFront = Statics.hardwareMap().dcMotor.get("motorRF");
        //self.mRightBack = Statics.hardwareMap().dcMotor.get("motorRB");
        self.mStrafe = Statics.hardwareMap().dcMotor.get("motorStrafe");
        return self;
    }

    public void DriveXYR(double x, double y, double r) {
        self.mLeftFront.setPower(r - y);
        self.mRightFront.setPower(y + r);
        self.mStrafe.setPower(x);
    }
}
