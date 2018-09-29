package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.core.ClassHolder;
import org.firstinspires.ftc.teamcode.core.OpModeExtended;

@Autonomous(name = "Test", group = "Tests")
public class TestAuto extends OpModeExtended {

    public InputControlManager getInputControlManager() {
        return new AICM();
    }

    public ClassHolder getClassHolder() {
        return new TestHolder(this);
    }

    public class AICM extends OpModeExtended.AutoInputControlManager {
        public void autoinit() {
            file = null;
        }
        public void autoupdate() {

        }
    }
}
