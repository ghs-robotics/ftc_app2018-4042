package org.firstinspires.ftc.teamcode.core;

public class SensorManager {
    public OpModeExtended context;
    public SensorInterface sensorInterface;

    final int VALUE_HISTORY_SIZE = 10;
    final int SMOOTH_HISTORY_SIZE = 4;

    double[] values;
    double[] valueReadTimes;

    int valuesIndex;
    boolean firstLoopOverValues;

    double value;
    boolean smooth = false;

    public SensorManager(OpModeExtended context, SensorInterface sensorInterface) {
        this.context = context;
        this.sensorInterface = sensorInterface;
    }

    public void init() {
        sensorInterface.init();
        values = new double[VALUE_HISTORY_SIZE];
        valueReadTimes = new double[VALUE_HISTORY_SIZE];
        valuesIndex = -1;
        firstLoopOverValues = true;
    }

    public void update() {
        sensorInterface.update();
        values[++valuesIndex] = sensorInterface.getCMValue();
        valueReadTimes[valuesIndex] = context.getRuntime();

        if (valuesIndex == VALUE_HISTORY_SIZE - 1) {
            firstLoopOverValues = false;
            valuesIndex = -1;
        }

        // smoothing is just an average of the last SMOOTH_HISTORY_SIZE
        // values for now. It doesn't yet take into account the times at
        // which the values were read.
        int tempSmoothHistory = smooth ? SMOOTH_HISTORY_SIZE : 1;
        double[] smoothValues = getLastNValues(tempSmoothHistory);
        value = 0;
        for (double reading : smoothValues) {
            value += reading / ((double) tempSmoothHistory);
        }
    }

    public double getCM() {
        return value;
    }

    public double[] getLastNValues(int n) {
        if (n > VALUE_HISTORY_SIZE
                || (firstLoopOverValues && n > valuesIndex + 1)
                || (n < 1))
            throw new IndexOutOfBoundsException("n out of bounds");

        double[] result = new double[n];

        for (int i = 0; i < n; i++) {
            result[i] = values[(valuesIndex - i + VALUE_HISTORY_SIZE)
                    % VALUE_HISTORY_SIZE];
        }

        return result;
    }
}
