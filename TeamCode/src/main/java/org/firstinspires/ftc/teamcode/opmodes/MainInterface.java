package org.firstinspires.ftc.teamcode.opmodes;

import android.util.Log;

import org.firstinspires.ftc.teamcode.balldrive.DriveSubsystem;
import org.firstinspires.ftc.teamcode.core.path.PathData;
import org.firstinspires.ftc.teamcode.core.path.PathFactory;
import org.firstinspires.ftc.teamcode.core.path.PathState;
import org.majora320.tealisp.evaluator.JavaInterface;
import org.majora320.tealisp.evaluator.LispException;
import org.majora320.tealisp.evaluator.LispObject;
import org.majora320.tealisp.evaluator.LispObject.Number;
import org.majora320.tealisp.evaluator.StackFrame;

import static org.firstinspires.ftc.teamcode.balldrive.DriveSubsystem.Mode.*;

public class MainInterface extends JavaInterface {
    @Override
    public boolean isSupportedFunction(String function) {
        //Log.i("team-code", "Function: " + function);
        return "drive".equals(function) || "log".equals(function);
    }

    private DriveSubsystem drive;

    public void setDrive(DriveSubsystem drive) {
        this.drive = drive;
    }

    /**
     * Calls Java functions in auto
     * @param name The name of the function to call
     * @param params The parameters for that function
     * @param frame The function stack
     * @return If the function has been completed
     * @throws LispException If the parameters given are not of the correct type
     */
    @Override
    public LispObject runFunction(String name, LispObject[] params, StackFrame frame) throws LispException {
        //Log.i("team-code", "name: " + name);
        switch(name) {
            case "drive":
                return driveFun(params);
            case "log":
                return logFun(params);
            case "path":
                return pathFun(params);
            default:
                return null;
        }
    }

    /**
     * Follows a path specified by the parameters
     * @param params (1) Initial position (2) Initial velocity (3) Final position (4) Final velocity
     *               (5) Maximum velocity (6) Maximum acceleration (7) Time step (.1 recommended)
     * @return Whether the path has been completed
     * @throws LispException If the parameters aren't all doubles
     */
    private LispObject pathFun(LispObject[] params) throws LispException {
        checkParams("path", params,
                new Class[]{ Number.class, Number.class, Number.class, Number.class, Number.class, Number.class, Number.class, },
                false);
        double[] processed = getAsDoubles(params);

        switch ((DriveSubsystem.Mode) drive.getSetting("mode")) {
            case AUTO_IDLE:
                drive.setting("mode", DriveSubsystem.Mode.AUTO_PATH_INIT);
                drive.setting("initPos",  processed[0]);
                drive.setting("initVel",  processed[1]);
                drive.setting("finalPos", processed[2]);
                drive.setting("finalVel", processed[3]);
                drive.setting("maxVel",   processed[4]);
                drive.setting("maxAccel", processed[5]);
                drive.setting("timestep", processed[6]);
                return new LispObject.Boolean(false);
            case AUTO_PATH_STOP:
                return new LispObject.Boolean(true);
            default:
                return new LispObject.Boolean(false);
        }
    }

    private LispObject logFun(LispObject[] params) throws LispException {
        checkParams("log", params, new Class[]{ LispObject.class }, false);
        Log.i("team-code", params[0].toString());
        return new LispObject.Void();
    }

    private LispObject driveFun(LispObject[] params) throws LispException {
        checkParams("drive", params,
                new Class[]{ Number.class, Number.class, Number.class, Number.class}, false);

        double[] processed = getAsDoubles(params);

        switch ((DriveSubsystem.Mode) drive.getSetting("mode")) {
            case AUTO_IDLE:
                drive.setting("mode", DriveSubsystem.Mode.AUTO_LRS_INIT);
                drive.setting("autoL", processed[0]);
                drive.setting("autoR", processed[1]);
                drive.setting("autoS", processed[2]);
                drive.setting("autoT", processed[3]);
                return new LispObject.Boolean(false);
            case AUTO_LRS_STOP:
                return new LispObject.Boolean(true);
            default:
                return new LispObject.Boolean(false);

        }
    }

    public double[] getAsDoubles(LispObject[] params) {
        double[] processed = new double[params.length];
        for (int i = 0; i < params.length; i++) {
            LispObject param = params[i];
            if (param instanceof LispObject.Integer) {
                processed[i] = ((LispObject.Integer) param).getValue();
            } else if (param instanceof LispObject.Double) {
                processed[i] = ((LispObject.Double) param).getValue();
            }
        }
        return processed;
    }
}
