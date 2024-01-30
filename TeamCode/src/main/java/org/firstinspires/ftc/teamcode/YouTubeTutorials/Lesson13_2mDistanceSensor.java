package org.firstinspires.ftc.teamcode.YouTubeTutorials;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Disabled
@TeleOp (group = "Tutorials")
public class Lesson13_2mDistanceSensor extends LinearOpMode {
    private DistanceSensor distanceSensor;
    private int distanceSensorTarget = 10;

    private Servo servoOne;
    private double servoOneInitPosition = 0.5;
    private double servoOnePositionOne = 0.0;
    private double servoOnePositionTwo = 1.0;

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while(!isStarted()) {
            distanceTelemetry();
        }
        waitForStart();
        while(opModeIsActive()) {
            teleOpControls();
            distanceTelemetry();
        }
    }

    public void initHardware() {
        initServoOne();
        initDistanceSensor();
    }


    public void distanceTelemetry() {
        telemetry.addData("Distance in CM", "%.2f", distanceSensor.getDistance(DistanceUnit.CM)); // distance in centimeters
        telemetry.update();
    }

    public void initServoOne() {
        servoOne = hardwareMap.get(Servo.class, "servoOne");
        servoOne.setDirection(Servo.Direction.FORWARD);
        servoOne.setPosition(servoOneInitPosition);
    }

    public void initDistanceSensor() {
        distanceSensor = hardwareMap.get(DistanceSensor.class, "distanceSensor");
    }

    public void teleOpControls() {
        if (distanceSensor.getDistance(DistanceUnit.CM) < distanceSensorTarget) {
            servoOne.setPosition(servoOnePositionTwo);
        }
        else {
            servoOne.setPosition(servoOnePositionOne);
        }
    }

}
