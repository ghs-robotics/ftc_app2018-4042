package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.core.path.PathData;
import org.firstinspires.ftc.teamcode.core.path.PathFactory;
import org.firstinspires.ftc.teamcode.core.path.PathState;

@TeleOp(name="Path Test", group="teleop")
public class PathTest extends OpMode {

    private DcMotorEx motor;
    private double startTime;
    PathData data;

    @Override
    public void init() {
        data = new PathFactory(0, 0, 100, 0, 10, 2, .01).data;
        motor = (DcMotorEx) hardwareMap.dcMotor.get("motorStrafe");
    }

    @Override
    public void start() {
        //startTime = getTime();
        startTime = System.currentTimeMillis() / 10;
    }

    @Override
    public void loop() {
        double currTime = (System.currentTimeMillis() - startTime * 10) / 10;

        PathState nextState = data.states.get((int) (currTime / .01));

        motor.setVelocity(nextState.vel, AngleUnit.DEGREES);

        telemetry.log().add(currTime + " " + data.states.size() + " " + (int) (currTime / .01));
    }

    private double getTime() {
        return System.currentTimeMillis() / 1000;
    }
}
