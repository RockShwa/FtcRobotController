package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch07Motor;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Ch7MotorTeleOp extends OpMode {
    /** 1) Add a method to the ProgrammingBoard that allows you to change the
     * ZeroPowerBehavior of the motor, and then add to your OpMode where
     * pressing gamepad1.a sets it to BRAKE and gamepad1.b sets it to FLOAT.
     *
     * 2) Make the joystick less sensitive in the middle without losing range by
     * bringing in the squareInputWithSign() method from section 5.2 into your
     * opMode and using it.
     */

    // Set up exercises with a Programming Board and a TeleOp :)
    ProgrammingBoardCh7 pb7 = new ProgrammingBoardCh7();

    public void init() {
        pb7.init(hardwareMap);
    }

    public void loop() {
        double motorSpeed = gamepad1.left_stick_y;
        pb7.setMotorSpeed(motorSpeed);
        telemetry.addData("Speed", motorSpeed);
        double motorSpeed2 = squareInputWithSign(gamepad1.left_stick_y);

        if (gamepad1.a) {
            pb7.changeZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            telemetry.addData("ZeroPower", "Brake");
        }
        if (gamepad1.b) {
            pb7.changeZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            telemetry.addData("ZeroPower", "Float");
        }
        if(gamepad1.x) {
            pb7.setMotorSpeed(motorSpeed2);
        }
    }

    double squareInputWithSign(double input){
        double output = input * input;
        if(input < 0){
            output = output * -1;
        }
        return output;
    }

}
