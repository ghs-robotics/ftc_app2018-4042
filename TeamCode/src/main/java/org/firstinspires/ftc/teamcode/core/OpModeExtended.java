package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.io.File;

public abstract class OpModeExtended extends OpMode {
    public InputControlManager inputControlManager;
    public ClassHolder classHolder;

    public abstract InputControlManager getInputControlManager();
    public abstract ClassHolder getClassHolder();

    public final void init() {
        this.inputControlManager = getInputControlManager();
        this.classHolder = getClassHolder();
        Registry.reset();
        Registry.grabData(classHolder);
        Registry.initSensors();
        Registry.initSubsystems();
        inputControlManager.init();
    }

    public final void loop() {
        Registry.updateSensors();
        Registry.updateSubsystemData();
        inputControlManager.update();
        Registry.updateSubsystemActuators();
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
