package org.firstinspires.ftc.teamcode.opmodes;

import android.util.Log;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.balldrive.DriveSubsystem;
import org.firstinspires.ftc.teamcode.core.path.PathData;
import org.firstinspires.ftc.teamcode.core.path.PathFactory;
import org.firstinspires.ftc.teamcode.core.path.PathState;
import org.majora320.tealisp.evaluator.JavaInterface;
import org.majora320.tealisp.evaluator.LispException;
import org.majora320.tealisp.evaluator.LispObject;
import org.majora320.tealisp.evaluator.LispObject.Number;
import org.majora320.tealisp.evaluator.StackFrame;

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

        //Runs the first time; calculates the path
        if (drive.getSetting("mode").equals(DriveSubsystem.Mode.AUTO_STOP)) {
            PathFactory factory = new PathFactory(processed[0], processed[1], processed[2], processed[3], processed[4], processed[5], processed[6]);
            drive.setting("path", factory.data); //create and set path
            drive.setting("startTime", getTime());
            drive.setting("mode", DriveSubsystem.Mode.AUTO_PATH);
        }

        //Runs the path each loop cycle on the strafe motor
        double currTime = getTime() - (double) drive.getSetting("startTime");

        PathState nextState = ((PathData) drive.getSetting("path")).getForTime(currTime);

        drive.setting("s", nextState.vel);

        if (nextState.equals(PathState.END_POINT)) { //Stop the robot from moving and reset to stop mode
            drive.setting("mode", DriveSubsystem.Mode.AUTO_STOP);
            return new LispObject.Boolean(true);
        } else {
            return new LispObject.Boolean(false);
        }
    }

    private double getTime() {
        return System.currentTimeMillis() / 1000.0; //convert to seconds
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

        ElapsedTime timer = (ElapsedTime) drive.getSetting("timer");

        // Runs the first time and starts the timer
        if (drive.getSetting("mode").equals(DriveSubsystem.Mode.AUTO_STOP)) {
            timer.reset();
            drive.setting("mode", DriveSubsystem.Mode.AUTO_LRS);
        }

        Log.i("team-code", "timer: " + timer.seconds());
        // Runs while the timer is less than the target time
        if (timer.seconds() <= processed[3]) {
            drive.setting("l", processed[0]);
            drive.setting("r", processed[1]);
            drive.setting("s", processed[2]);
            Log.i("team-code", "" + false);
            return new LispObject.Boolean(false);
        } else { // Stops the robot and waits
            drive.setting("mode", DriveSubsystem.Mode.AUTO_STOP);
            drive.setting("l", 0);
            drive.setting("r", 0);
            drive.setting("s", 0);
            Log.i("team-code", "" + true);
            return new LispObject.Boolean(true);
        }
    }

    public double[] getAsDoubles(LispObject[] params) {
        double[] processed = new double[params.length];
        for (int i = 0; i < params.length; i++) {
            LispObject param = params[i];
            if (param instanceof LispObject.Integer) {
                processed[i] = ((LispObject.Integer) param).value;
            } else if (param instanceof LispObject.Double) {
                processed[i] = ((LispObject.Double) param).value;
            }
        }
        return processed;
    }
}
