package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Stores variables which are traditionally confined to the access of the opmode class alone
 * eg: telemetry, gamepad
 */
public class Statics {

    private Telemetry telemetry;
    private Gamepad gamepad1;
    private Gamepad gamepad2;
    private HardwareMap hardwareMap;

    public static Statics self;

    private Statics() { }

    private static Statics get() {
        if (self == null) {
            self = new Statics();
        }
        return self;
    }

    public static void set(Telemetry telemetry, Gamepad gamepad1, Gamepad gamepad2, HardwareMap hardwareMap) {
        Statics statics = Statics.get();
        statics.telemetry = telemetry;
        statics.gamepad1 = gamepad1;
        statics.gamepad2 = gamepad2;
        statics.hardwareMap = hardwareMap;
    }

    public static Gamepad gamepad1() {
        return Statics.get().gamepad1;
    }

    public static Gamepad gamepad2() {
        return Statics.get().gamepad2;
    }

    public static Telemetry telemetry() {
        return Statics.get().telemetry;
    }

    public static HardwareMap hardwareMap() {
        return Statics.get().hardwareMap;
    }
}
