package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch14Inheritance;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.ArrayList;

public class TestWiring extends OpMode {
    /**
     * Add a test for the touchSensor. you’ll need a TestDigitalChannel class and
     * add it to the getTests() method in ProgrammingBoard. (No change needed
     * to OpMode)
     * 2. Add a test for the servo, you’ll need a TestServo class - hint your constructor probably needs an “on” value and an “off” value for the servo. You’ll
     * also need to add it to the getTests()
     * 3. Change ProgrammingBoard2 through ProgrammingBoard9 to derive from
     * the one before it (ie, ProgrammingBoard2 extends ProgrammingBoard1)
     * adding only what is necessary each time. Make sure all your OpModes
     * still work!! (Hint: you’ll have to change private members to protected so
     * the child can access it)
     */
    ProgrammingBoardCh14 board = new ProgrammingBoardCh14();
    ArrayList<TestItem> tests;
    boolean wasDown, wasUp;
    int testNum; //Index of test list

    public void init() {
        board.init(hardwareMap);
        tests = board.getTests();
    }

    public void loop() {
        moveUpOnTestList();
        moveDownOnTestList();
        telemetryInstructions();

    }
    public void moveUpOnTestList() {
        // move up in list of test
        if (gamepad1.dpad_up && !wasUp) {
            testNum--;
            if (testNum < 0) {
                testNum = tests.size() -1; // I think this circles it back
            }
        }
        wasUp = gamepad1.dpad_up;

    }
    public void moveDownOnTestList() {
        // move down in list of tests
        if (gamepad1.dpad_down && !wasDown) {
            testNum++;
            if (testNum >= tests.size()) {
                testNum = 0; // I think this circles it back
            }
        }
        wasDown = gamepad1.dpad_down;

    }

    public void telemetryInstructions() {
        //Put instructions on the telemetry
        telemetry.addLine("Use Up and Down on D-pad to cycle through choices");
        telemetry.addLine("Press A to run test");
        //put the test on the telemetry
        TestItem currTest = tests.get(testNum);
        telemetry.addData("Test:", currTest.getDescription());
        //run or don’t run based on a
        currTest.run(gamepad1.a, telemetry);
    }
}
