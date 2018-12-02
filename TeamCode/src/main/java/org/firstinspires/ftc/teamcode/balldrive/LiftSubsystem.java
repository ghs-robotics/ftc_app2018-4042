package org.firstinspires.ftc.teamcode.balldrive;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.core.OpModeExtended;
import org.firstinspires.ftc.teamcode.core.structure.Setting;
import org.firstinspires.ftc.teamcode.core.structure.Subsystem;

public class LiftSubsystem extends Subsystem {

    private OpModeExtended context;
    public LiftActuator actuator;

    private ElapsedTime releaseTimer;
    private ElapsedTime powerTimer;

    public enum ReleaseStage { CLOSED, SLIDE_OPEN, OPENING, OPEN }

    @Setting
    public ReleaseStage releaseMode;

    @Setting
    public double power;

    @Setting
    public double powerTime;

    public LiftSubsystem(OpModeExtended context) {
        super(context);
        this.context = context;
        this.actuator = new LiftActuator(context);
    }

    @Override
    public void init() {
        actuator.init();

        releaseMode = ReleaseStage.CLOSED;
        power = 0;
        powerTime = 0;
        releaseTimer = new ElapsedTime();
        powerTimer = new ElapsedTime();
    }

    @Override
    public void updateActuators() {
        switch (releaseMode) {
            case SLIDE_OPEN: //Start sliding open by moving the motor
                actuator.setLift(1);
                releaseTimer.reset();
                releaseMode = ReleaseStage.OPENING;
            case OPENING: //When enough time has passed, open the servo
                if (releaseTimer.seconds() > 1) { //TODO: TUNE THIS
                    actuator.setLift(0);
                    actuator.open();
                    releaseMode = ReleaseStage.OPEN;
                    powerTimer.reset();
                }
            case OPEN: //If the servo's open, then:
                if (powerTime != 0) { //If the lift is meant to be run with a timer
                    if (powerTimer.seconds() < powerTime) { //Run if the time is less than the target
                        actuator.setLift(power);
                    } else { //Otherwise, reset everything and stop running with a timer
                        powerTime = 0;
                        power = 0;
                    }
                } else { //Otherwise, just set the power to what it should be
                    actuator.setLift(power);
                }
        }
    }

    @Override
    public void updateData() {

    }
}
