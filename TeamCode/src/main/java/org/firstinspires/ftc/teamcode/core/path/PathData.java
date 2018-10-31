package org.firstinspires.ftc.teamcode.core.path;

import java.util.ArrayList;

public class PathData {
    public ArrayList<PathState> states;

    PathData() {
        this(new ArrayList<>());
    }

    private PathData(ArrayList<PathState> states) {
        this.states = states;
    }
}