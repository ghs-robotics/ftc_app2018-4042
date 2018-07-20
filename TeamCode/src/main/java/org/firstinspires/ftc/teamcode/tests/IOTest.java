package org.firstinspires.ftc.teamcode.tests;

import org.firstinspires.ftc.teamcode.io.input.AutoFileReader;
import org.firstinspires.ftc.teamcode.io.output.LoggerThread;

public class IOTest {

    public static void main(String[] args) {
        AutoFileReader.get().setFile("input234.txt");
        //System.out.println(AutoFileReader.get().advanceOp());
        //System.out.println(AutoFileReader.get().advanceOp());
        //System.out.println(AutoFileReader.get().getCurrOp());

        try { Thread.sleep(5000); } catch (Exception ex) { ex.printStackTrace(); }

        LoggerThread.RUN = false;
    }
}
