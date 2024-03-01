package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch10ColorAndDistance;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * 2. Make the motor stop when the distance sensor sees something closer than
 *  * 10cm and go at half speed when farther than that.
 */
public class TeleOpCh10 extends OpMode {
    ProgrammingBoardCh10 board = new ProgrammingBoardCh10();

    public void init() {
        board.init(hardwareMap);
    }

    public void loop() {
        telemetry.addData("Amount red", board.getAmountRed());
        telemetry.addData("Amount blue", board.getAmountBlue());
        telemetry.addData("Distance (CM)", board.getDistance(DistanceUnit.CM));
        telemetry.addData("Distance (IN)", board.getDistance(DistanceUnit.INCH));

        double distanceCM = board.getDistance(DistanceUnit.CM);
        if(distanceCM < 10) {
            board.setMotorSpeed(0);
        }
        else {
            board.setMotorSpeed(0.5);
        }
        telemetry.addData("Distance (CM)", distanceCM);
    }
}
