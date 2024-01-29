package org.firstinspires.ftc.teamcode.YouTubeTutorials;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Disabled
@TeleOp (group = "Tutorials")
public class Lesson09_MaxVelocityWithDcMotor extends LinearOpMode {
    private DcMotorEx motorFour; //removes PID so we can tune it
    private double motorFourZeroPower = 0.0;
    private double motorFourMaxPower = 1.0;
    private double motorFourCurrentVelocity = 0.0;
    private double motorFourMaxVelocity = 0.0;

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while(!isStarted()) {
            motorTelemetry();
        }
        waitForStart();
        while(opModeIsActive()) {
            motorFourMaxVelocityTest();
        }
    }

    public void initHardware() {
        initMotorFour();
    }

    public void initMotorFour() {
        motorFour = hardwareMap.get(DcMotorEx.class, "motorFour");
        motorFour.setDirection((DcMotorEx.Direction.FORWARD));
        motorFour.setPower(motorFourZeroPower);
        motorFour.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorFour.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFour.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void motorFourMaxVelocityTest() {
        motorFour.setPower(motorFourMaxPower);
        motorFourCurrentVelocity = motorFour.getVelocity();
        if (motorFourCurrentVelocity > motorFourMaxVelocity) {
            motorFourMaxVelocity = motorFourCurrentVelocity;
        }
    }
    public void motorTelemetry() {
        telemetry.addData("Current Power", motorFour.getPower());
        telemetry.addData("Maximum Velocity", motorFourMaxVelocity);
        telemetry.addData("Current Velocity", motorFourCurrentVelocity);
        telemetry.update();
    }
}
