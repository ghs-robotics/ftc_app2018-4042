package org.firstinspires.ftc.teamcode.control;

import org.firstinspires.ftc.teamcode.io.input.IOUtils;

import java.io.File;

import teascript.Tint;

import static org.firstinspires.ftc.teamcode.control.InputControlManager.subsystemTints;
import static teascript.Nice.*;

/**
 * Starts TeaScript running and calls an update function in the TeaScript file
 */
public class AutoInputManager {

    public static AutoInputManager self;

    private AutoInputManager(String autoFileName) {
        //reader = AutoFileReader.get();
        //reader.setFile(autoFileName);

        for (Tint subsystemTint : subsystemTints) {
            addTint(subsystemTint);
        }

        init(new File(IOUtils.FILE_ROOT, autoFileName));
    }

    /**
     * Singleton functionality.
     * Gets the instance of this class
     * @param autoFileName The name of the file to read from
     * @return The only extant instance of the class
     */
    public static AutoInputManager get(String autoFileName) {
        if (self == null) {
            self = new AutoInputManager(autoFileName);
        }
        return self;
    }

    /**
     * Calls an update function in the appropriate TeaScript file
     */
    public void update() {
        executeAction("main()");
    }
}
