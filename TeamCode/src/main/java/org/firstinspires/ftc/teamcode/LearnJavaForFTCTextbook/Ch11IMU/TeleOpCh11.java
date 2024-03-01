package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch11IMU;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * 1. Change the OpMode to also show the heading in RADIANS as well as
 *  * DEGREES
 * 2. Make the motor stopped when our heading is 0, go negative when our
 * heading is negative, and positive when our heading is positive.
 */
public class TeleOpCh11 extends OpMode {
    ProgrammingBoardCh11 board = new ProgrammingBoardCh11();

    public void init() {
        board.init(hardwareMap);
    }

    public void loop() {
        double robotHeadingDegrees = board.getHeading(AngleUnit.DEGREES);
        double motorSpeed = Range.scale(robotHeadingDegrees, -180, 180, -1.0, 1);
        board.setMotorSpeed(motorSpeed);

        telemetry.addData("Our Heading (D)", board.getHeading(AngleUnit.DEGREES));
        telemetry.addData("Our Heading (R)", board.getHeading(AngleUnit.RADIANS));
        telemetry.addData("Motor Speed", motorSpeed);
    }
}
