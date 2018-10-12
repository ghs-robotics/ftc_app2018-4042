package org.firstinspires.ftc.teamcode.core;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.io.File;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

public abstract class OpModeExtended extends OpMode {
    public InputControlManager inputControlManager;
    public ClassHolder classHolder;
    public GamepadExtended gamepadExtended1;
    public GamepadExtended gamepadExtended2;

    public abstract InputControlManager getInputControlManager();
    public abstract ClassHolder getClassHolder();

    public final void init() {
        try {
            this.inputControlManager = getInputControlManager();
            this.classHolder = getClassHolder();

            this.gamepadExtended1 = new GamepadExtended(gamepad1);
            this.gamepadExtended2 = new GamepadExtended(gamepad2);
            gamepadExtended1.update();
            gamepadExtended2.update();

            Registry.grabData(classHolder);
            Registry.initSensors();
            Registry.initSubsystems();
            inputControlManager.init();
        } catch (Exception e) {
            Log.e("team-code-init-error", getStackTrace(e));
        }
    }

    public final void loop() {
        try {
            gamepadExtended1.update();
            gamepadExtended2.update();
            Registry.updateSensors();
            Registry.updateSubsystemData();
            inputControlManager.update();
            Registry.updateSubsystemActuators();
        } catch (Exception e) {
            Log.e("team-code-main-error", getStackTrace(e));
        }
    }

    public void stop() {
        LogRecorder.writeLog();
    }

    public interface InputControlManager {
        void init();
        void update();
    }

    public abstract class AutoInputControlManager implements InputControlManager {
        public File file;

        public final void init() {
            autoinit();
            // TODO: init Tea system
        }
        public final void update() {
            autoupdate();
            // TODO: update Tea system
        }

        public abstract void autoinit();
        public abstract void autoupdate();
    }

    public abstract class TeleInputControlManager implements InputControlManager {
        public final void init() {
            teleinit();
        }

        public final void update() {
            teleupdate();
        }

        public abstract void teleinit();
        public abstract void teleupdate();
    }
}
