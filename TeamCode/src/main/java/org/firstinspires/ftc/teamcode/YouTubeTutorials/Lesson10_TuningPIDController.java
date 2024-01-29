package org.firstinspires.ftc.teamcode.YouTubeTutorials;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Disabled
@TeleOp (group = "Tutorials")
public class Lesson10_TuningPIDController extends LinearOpMode {
    private DcMotorEx motorFour; //removes PID so we can tune it
    private double motorFourZeroPower = 0.0;
    private double motorFourCurrentVelocity = 0.0;
    private double motorFourMaxVelocity = 0.0;

    // Running to a specific velocity means that the robot will hold at that velocity & PID maintains velocity
    // Better than using power because power runs out over time (battery life)
    // Recommendation: Target velocity should be no more than 80% of max velocity

    private double motorFourTargetVelocity = 800.0; //0.8 * resultMaxVelocityTest
    private double resultMaxVelocityTest = 2800.0; //enter max velocity from test
    private double F = 32767.0/resultMaxVelocityTest; // leave this alone

    // These math calculations are just to get in the ballpark; manually tune on robot
    private double kP = F * 0.1; // oscillation = P is too high
    private double kI = kP * 0.1; // nudges you to your target over time
    private double kD = kP * 0.01; // applies breaking force to control overshoot; keep this small to avoid noise
    private double position = 0.5;

    // manually tune P, then D, then I (salt, garlic, pepper)
    // increments of velocity is in 20s
    // we want to get to target velocity as fast as possible with minimum overshoot

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while(!isStarted()) {
            motorTelemetry();
        }
        waitForStart();
        while(opModeIsActive()) {
            runMotorFourMaxVelocity(motorFourTargetVelocity);
        }
    }

    public void initHardware() {
        initMotorFour(kP, kI, kD, F, position);
    }

    public void initMotorFour(double kP, double kI, double kD, double F, double position) {
        motorFour = hardwareMap.get(DcMotorEx.class, "motorFour");
        motorFour.setDirection((DcMotorEx.Direction.FORWARD));
        motorFour.setPower(motorFourZeroPower);
        motorFour.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorFour.setVelocityPIDFCoefficients(kP, kI, kD, F);
        motorFour.setPositionPIDFCoefficients(position);
        motorFour.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFour.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void runMotorFourMaxVelocity(double velocity) {
        motorFour.setVelocity(velocity); // runs to a specific velocity
        motorFourCurrentVelocity = motorFour.getVelocity();
        if (motorFourCurrentVelocity > motorFourMaxVelocity) {
            motorFourMaxVelocity = motorFourCurrentVelocity;
        }
    }
    public void motorTelemetry() {
        telemetry.addData("Current Power", motorFour.getPower());
        telemetry.addData("Target Velocity", motorFourTargetVelocity);
        telemetry.addData("Maximum Velocity", motorFourMaxVelocity);
        telemetry.addData("Current Velocity", motorFour.getVelocity());
        telemetry.addData("F", F);
        telemetry.addData("kP", kP);
        telemetry.addData("kI", kI);
        telemetry.addData("kD", kD);
        telemetry.update();
    }
}
