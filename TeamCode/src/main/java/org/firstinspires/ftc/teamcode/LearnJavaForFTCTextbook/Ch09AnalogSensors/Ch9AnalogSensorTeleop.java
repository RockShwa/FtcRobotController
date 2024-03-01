package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch09AnalogSensors;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Now make an OpMode that sets the servo to the position that the pot is
 * returning in that range. Then you can turn the pot and it will cause the
 * servo to “follow” it.
 */
public class Ch9AnalogSensorTeleop extends OpMode {
    ProgrammingBoardCh9 board = new ProgrammingBoardCh9();

    public void init() {
        board.init(hardwareMap);
    }

    public void loop() {
        telemetry.addData("Pot Angle", board.getPotAngle());
        telemetry.addData("Pot Value (Range)", board.getPotRange());
        board.setServoPosition(board.getPotRange());
    }

}
