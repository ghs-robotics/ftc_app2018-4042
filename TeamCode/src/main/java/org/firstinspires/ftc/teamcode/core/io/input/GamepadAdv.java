package org.firstinspires.ftc.teamcode.core.io.input;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Hazel on 6/24/2018.
 * Has accessors for (per digital reading):
 *     Button just pressed?
 *     Button just released?
 *     State of button at last ping (consistent with #1 and #2)
 *     Current state of button (NOT consistent with #1 and #2; do not use unless you know you need to)
 * Has accessors for (per analog reading):
 *     Current reading
 */

public class GamepadAdv {

    private boolean a, b, x, y, dpad_up, dpad_left, dpad_down, dpad_right, left_bumper, right_bumper,
        left_stick_button, right_stick_button, back, start, guide;
    private boolean oldA, oldB, oldX, oldY, oldDpad_up, oldDpad_left, oldDpad_down, oldDpad_right,
        oldLeft_bumper, oldRight_bumper, oldLeft_stick_button, oldRight_stick_button,
        oldBack, oldStart, oldGuide;

    //Initialized with the class
    // Used for fetching current state of button (but NOT button at last ping)
    private Gamepad gamepad;

    public GamepadAdv() { }
    public GamepadAdv(Gamepad gamepad) { this.gamepad = gamepad; }

    /**
     * Updates all of the button readings
     */
    public void update() {
        oldA = a;
        oldB = b;
        oldX = x;
        oldY = y;
        oldDpad_up = dpad_up;
        oldDpad_left = dpad_left;
        oldDpad_down = dpad_down;
        oldDpad_right = dpad_right;
        oldLeft_bumper = left_bumper;
        oldRight_bumper = right_bumper;
        oldLeft_stick_button = left_stick_button;
        oldRight_stick_button = right_stick_button;
        oldBack = back;
        oldStart = start;
        oldGuide = guide;

        a = gamepad.a;
        b = gamepad.b;
        x = gamepad.x;
        y = gamepad.y;
        dpad_up = gamepad.dpad_up;
        dpad_left = gamepad.dpad_left;
        dpad_down = gamepad.dpad_down;
        dpad_right = gamepad.dpad_right;
        left_bumper = gamepad.left_bumper;
        right_bumper = gamepad.right_bumper;
        left_stick_button = gamepad.left_stick_button;
        right_stick_button = gamepad.right_stick_button;
        back = gamepad.back;
        start = gamepad.start;
        guide = gamepad.guide;
    }

    /*
    These accessors fetch values from last ping
     */
    public boolean a() { return a; }
    public boolean b() { return b; }
    public boolean x() { return x; }
    public boolean y() { return y; }
    public boolean dpad_up() { return dpad_up; }
    public boolean dpad_left() { return dpad_left; }
    public boolean dpad_down() { return dpad_down; }
    public boolean dpad_right() { return dpad_right; }
    public boolean left_bumper() { return left_bumper; }
    public boolean right_bumper() { return right_bumper; }
    public boolean left_stick_button() { return left_stick_button; }
    public boolean right_stick_button() { return right_stick_button; }
    public boolean back() { return back; }
    public boolean start() { return start; }
    public boolean guide() { return guide; }

    /*
    These accessors fetch the current value straight off the gamepad the object was initialized with
    DO NOT USE UNLESS YOU'RE SURE YOU NEED THEM. THEY WILL NOT BEHAVE THE WAY YOU EXPECT THEM TO.
     */
    public boolean currA() { return gamepad.a; }
    public boolean currB() { return gamepad.b; }
    public boolean currX() { return gamepad.x; }
    public boolean currY() { return gamepad.y; }
    public boolean currDpad_up() { return gamepad.dpad_up; }
    public boolean currDpad_left() { return gamepad.dpad_left; }
    public boolean currDpad_down() { return gamepad.dpad_down; }
    public boolean currDpad_right() { return gamepad.dpad_right; }
    public boolean currLeft_bumper() { return gamepad.left_bumper; }
    public boolean currRight_bumper() { return gamepad.right_bumper; }
    public boolean currLeft_stick_button() { return gamepad.left_stick_button; }
    public boolean currRight_stick_button() { return gamepad.right_stick_button; }
    public boolean currBack() { return gamepad.back; }
    public boolean currStart() { return gamepad.start; }
    public boolean currGuide() { return gamepad.guide; }

    /*
    These accessors return if the button has just been PRESSED this loop cycle
     */
    public boolean aDown() { return a && !oldA; }
    public boolean bDown() { return b && !oldB; }
    public boolean xDown() { return x && !oldX; }
    public boolean yDown() { return y && !oldY; }
    public boolean dpad_upDown() { return dpad_up && !oldDpad_up; }
    public boolean dpad_leftDown() { return dpad_left && !oldDpad_left; }
    public boolean dpad_downDown() { return dpad_down && !oldDpad_down; }
    public boolean dpad_rightDown() { return dpad_right && !oldDpad_right; }
    public boolean left_bumperDown() { return left_bumper && !oldLeft_bumper; }
    public boolean right_bumperDown() { return right_bumper && !oldRight_bumper; }
    public boolean left_stick_buttonDown() { return left_stick_button && !oldLeft_stick_button; }
    public boolean right_stick_buttonDown() { return right_stick_button && !oldRight_stick_button; }
    public boolean backDown() { return back && !oldBack; }
    public boolean startDown() { return start && !oldStart; }
    public boolean guideDown() { return guide && !oldGuide; }

    /*
    These accessors return if the button has just been RELEASED this loop cycle
     */
    public boolean aUp() { return !a && oldA; }
    public boolean bUp() { return !b && oldB; }
    public boolean xUp() { return !x && oldX; }
    public boolean yUp() { return !y && oldY; }
    public boolean dpad_upUp() { return !dpad_up && oldDpad_up; }
    public boolean dpad_leftUp() { return !dpad_left && oldDpad_left; }
    public boolean dpad_downUp() { return !dpad_down && oldDpad_down; }
    public boolean dpad_rightUp() { return !dpad_right && oldDpad_right; }
    public boolean left_bumperUp() { return !left_bumper && oldLeft_bumper; }
    public boolean right_bumperUp() { return !right_bumper && oldRight_bumper; }
    public boolean left_stick_buttonUp() { return !left_stick_button && oldLeft_stick_button; }
    public boolean right_stick_buttonUp() { return !right_stick_button && oldRight_stick_button; }
    public boolean backUp() { return !back && oldBack; }
    public boolean startUp() { return !start && oldStart; }
    public boolean guideUp() { return !guide && oldGuide; }

    /*
    These accessors work for analog readings
     */
    public double left_stick_x() { return gamepad.left_stick_x; }
    public double left_stick_y() { return gamepad.left_stick_y; }
    public double right_stick_x() { return gamepad.right_stick_x; }
    public double right_stick_y() { return gamepad.right_stick_y; }
    public double left_trigger() { return gamepad.left_trigger; }
    public double right_trigger() { return gamepad.right_trigger; }
}
