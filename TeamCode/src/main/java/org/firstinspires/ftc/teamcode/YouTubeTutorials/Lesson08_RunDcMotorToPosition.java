package org.firstinspires.ftc.teamcode.YouTubeTutorials;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@TeleOp(group = "Tutorials")
public class Lesson08_RunDcMotorToPosition extends LinearOpMode {
    private DcMotor motorThree;
    private double motorThreeZeroPower = 0.0;
    private double motorThreePower = 1.0;
    private int motorThreePositionOne = 0;
    private int motorThreePositionTwo = 1000;

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while(!isStarted()) {
            motorTelemetry();
        }
        waitForStart();
        while(opModeIsActive()) {
            teleOpControls();
            motorTelemetry();
        }
    }

    public void initHardware() {
        initMotorThree();
    }

    public void initMotorThree() {
        motorThree = hardwareMap.get(DcMotor.class, "motorThree");
        motorThree.setDirection((DcMotor.Direction.FORWARD));
        motorThree.setPower(motorThreeZeroPower);
        motorThree.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorThree.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorThree.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void motorTelemetry() {
        // 2d = 2 decimal places (double format), 2f = 2 decimal places (float format)
        telemetry.addData("Note", "Tap Y to reset encoders.");
        telemetry.addData("motorThree", "Encoder: %2d, Power: %.2f", motorThree.getCurrentPosition(), motorThree.getPower());
        telemetry.update();
    }

    public void teleOpControls() {
        if (gamepad2.left_bumper) {
            runMotorThreeToPosition(motorThreePositionOne);
        }
        if (gamepad2.right_bumper) {
            runMotorThreeToPosition(motorThreePositionTwo);
        }
        if (gamepad2.y) {
            resetEncoders();
        }
    }

    public void runMotorThreeToPosition(int position) {
        motorThree.setTargetPosition(position);
        motorThree.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorThree.setPower(motorThreePower);
        while (motorThree.isBusy()) {
            motorTelemetry();
        }
        motorThree.setPower(motorThreeZeroPower); // optional, you may want it to hold position, if so, delete this
    }

    public void resetEncoders() {
        stopMotors();
        stopAndResetEncoders();
        resetMode();
    }

    public void stopMotors() {
        motorThree.setPower(motorThreeZeroPower);
    }
    public void stopAndResetEncoders() {
        motorThree.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void resetMode() {
        motorThree.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

}
