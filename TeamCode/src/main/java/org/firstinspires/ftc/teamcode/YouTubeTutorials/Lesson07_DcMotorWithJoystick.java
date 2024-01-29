package org.firstinspires.ftc.teamcode.YouTubeTutorials;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@TeleOp(group = "Tutorials")
public class Lesson07_DcMotorWithJoystick extends LinearOpMode {
    private DcMotor motorTwo;
    private double motorTwoZeroPower = 0.0;
    private double motorTwoSensitivity = 0.5;

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
        initMotorTwo();
    }

    public void initMotorTwo() {
        motorTwo = hardwareMap.get(DcMotor.class, "motorTwo");
        motorTwo.setDirection((DcMotor.Direction.REVERSE));
        motorTwo.setPower(motorTwoZeroPower);
        motorTwo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT); //lets friction do the stopping
        motorTwo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // general PID controller used to achieve a specified power using encoder data
    }
    public void motorTelemetry() {
        // 2d = 2 decimal places (double format), 2f = 2 decimal places (float format)
        telemetry.addData("motorTwo", "Encoder: %2d, Power: %.2f", motorTwo.getCurrentPosition(), motorTwo.getPower());
        telemetry.update();
    }

    public void teleOpControls() {
        motorTwo.setPower((gamepad1.right_stick_y * motorTwoSensitivity));
    }

}
