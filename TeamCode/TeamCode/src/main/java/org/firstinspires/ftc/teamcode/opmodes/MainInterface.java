package org.firstinspires.ftc.teamcode.opmodes;

import android.util.Log;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.balldrive.DriveSubsystem;
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
                checkParams("drive", params,
                        new Class[]{ Number.class, Number.class, Number.class, Number.class}, false);

                double[] processed = getAsDouble(params);

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
            case "log":
                checkParams("log", params, new Class[]{ LispObject.class }, false);
                Log.i("team-code", params[0].toString());
                return new LispObject.Void();
            default:
                return null;
        }
    }

    public double[] getAsDouble(LispObject[] params) {
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
