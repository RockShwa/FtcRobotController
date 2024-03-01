package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch08Servo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class Ch8ServoTeleOp extends OpMode{

    /**
     2. Change the opMode so that how far you push in gamepad1.left_trigger
     determines the position of the servo.
     */
    ProgrammingBoardCh8 board = new ProgrammingBoardCh8();

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        board.setServoPosition(gamepad1.left_trigger); // How does this change if I use: while(gamepad1.left_trigger > 0)?
    }
}
