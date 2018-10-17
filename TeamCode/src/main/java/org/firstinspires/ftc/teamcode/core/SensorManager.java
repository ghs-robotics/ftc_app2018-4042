package org.firstinspires.ftc.teamcode.core;

public class SensorManager {
    public SensorInterface sensorInterface;
    public OpModeExtended context;

    final int VALUE_HISTORY_SIZE = 10;
    final int SMOOTH_HISTORY_SIZE = 4;

    double[] values;
    double[] valueReadTimes;

    int valuesIndex;
    boolean firstLoopOverValues;

    double value;
    boolean smooth = false;

    public SensorManager(SensorInterface sensorInterface, OpModeExtended context) {
        this.sensorInterface = sensorInterface;
        this.context = context;
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

    public double[][] multiplyMatrix(double[][] matrix1, double[][] matrix2){
        int row1 = matrix1.length;
        int col1row2 = matrix1[0].length;
        int col2 = matrix2[0].length;
        double[][] product = new double[row1][col2];
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col2; j++) {
                for (int k = 0; k < col1row2; k++) {
                    product[i][j] +=  matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return product;
    }

    public double[][] transposeMatrix(double[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;
        double[][] transpose = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                transpose[j][i] = matrix[i][j];

            }
        }
        return transpose;
    }

    public double[][] invert2x2Matrix(double[][] matrix){
        double determinant = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        double[][] inverse = new double[][]{
                { matrix[1][1] / determinant, -1 * matrix[1][0] / determinant},
                { -1 * matrix[0][1] / determinant, matrix[0][0] / determinant}
        };
        return inverse;
    }

    public double[][] addMatrix(double[][] matrix1, double[][] matrix2){
        int row = matrix1.length;
        int col = matrix1[0].length;
        double[][] sum = new double[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++) {
                sum[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return sum;
    }

    public double[][] subtractMatrix(double[][] matrix1, double[][] matrix2){
        int row = matrix1.length;
        int col = matrix1[0].length;
        double[][] difference = new double[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++) {
                difference[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return difference;
    }




    public double[][] initializeKalmanMatrices(SensorInterface sensor, double px, double py, double vx,
                                            double vy, double r, double dt, double errorPx, double errorPy,
                                            double errorVx, double errorVy){
        double[][] previousPrediction = new double[][]{
                { px },
                { py },
                { vx },
                { vy }
        };
        double[][] transtionState = new double[][]{
                { 1, 0, dt, 0 },
                { 0, 1, 0, dt },
                { 0, 0, 1, 0  },
                { 0, 0, 0, 1  }
        };
        double[][] stateScalar = new double[][]{
                { 1, 0, 0, 0 },
                { 0, 1, 0, 0 }
        };
        double[][] predictedError = new double[][]{
                { errorPx, 0, 0, 0 },
                { 0, 0, errorPy, dt },
                { 0, 0, errorVx, 0  },
                { 0, 0, 0, errorVy  }
        };
        double[][] measurementCovariance = new double[][]{
                { r, 0},
                { 0, r}
        };

        double[][] identityMatrix = new double[][]{
                {1, 0},
                {0, 1}
        };
        return identityMatrix;
    }

     public double[][] predictState(double[][] transitionState, double[][] previousPrediction){
        double[][] statePrediction = multiplyMatrix(transitionState, previousPrediction);
        return statePrediction;
     }

    public double[][] predictError(double[][] predictedError, double[][] transitionState){
        double[][] errorPrediction = multiplyMatrix(multiplyMatrix(transitionState, predictedError), transposeMatrix(transitionState));
        return errorPrediction;
    }

    public double[][] updateGain(double[][] errorPrediction, double[][] stateScalar,
                                 double[][] measurementCovariance){
        double[][] stateGain = multiplyMatrix(multiplyMatrix(errorPrediction,transposeMatrix(stateScalar)),
                invert2x2Matrix(addMatrix(multiplyMatrix(multiplyMatrix(stateScalar, errorPrediction),
                        transposeMatrix(stateScalar)), measurementCovariance)));
        return stateGain;
    }

    public double[][] updateError(double[][] identityMatrix, double[][] previousError,
                                  double[][] stateScalar, double[][] stateGain){
        double[][] updatedError = multiplyMatrix(subtractMatrix(identityMatrix, multiplyMatrix(stateGain, stateScalar)), previousError);
        return updatedError;
    }

    // public double[][] updatePrediction()
}
//Pk←(I−GkC)Pk
