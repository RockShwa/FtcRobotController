package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch14Inheritance;

import org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch12State.ProgrammingBoardCh12;

import java.util.ArrayList;

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
public class ProgrammingBoardCh14 extends ProgrammingBoardCh12 {
    public ArrayList<TestItem> getTests() {
        ArrayList<TestItem> tests = new ArrayList<>();
        tests.add(new TestMotor("PB Motor", 0.5, motor));
        tests.add(new TestDigitalChannel("Digital Channel (Touch Sensor)", touchSensor));
        tests.add(new TestServo("Servo", servo, 1.0, 0.0));
        return tests;
    }

}
