package org.firstinspires.ftc.teamcode.balldrive;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.core.OpModeExtended;
import org.firstinspires.ftc.teamcode.core.Subsystem;
import org.firstinspires.ftc.teamcode.core.io.input.GamepadExtended;

import java.util.Map;

public class DriveSubsystem extends Subsystem {
    private OpModeExtended context;
    private Telemetry telemetry;
    private GamepadExtended gamepadA, gamepadB;

    private DriveInterface driveInterface;

    private double leftX, leftY, rightX, rightY;

    //@Setting
    public boolean xyr;

    public DriveSubsystem(OpModeExtended context) {
        this.context = context;
        this.telemetry = context.telemetry;
        driveInterface = new DriveInterface(context);
    }

    public void init() {
        this.gamepadA = context.gamepadExtended1;
        this.gamepadB = context.gamepadExtended2;

        leftX = 0;
        leftY = 0;
        rightX = 0;
        rightY = 0;
    }

    public void updateData() {
        leftX = gamepadA.left_stick_x;
        leftY = gamepadA.left_stick_y;
        rightX = gamepadA.right_stick_x;
        rightY = gamepadA.right_stick_y;
    }

    public void updateActuators() {
        if (xyr) {
            driveInterface.drive(rightX - leftY, rightX + leftY, leftX);
        }
        else {
            driveInterface.drive(-leftY, rightY, (leftX + rightX)/2);
        }
    }
}
