package org.firstinspires.ftc.teamcode.YouTubeTutorials;

import android.hardware.Sensor;
import android.text.method.Touch;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Disabled
@TeleOp(group = "Tutorials")
public class Lesson15_REVTouchSensor extends LinearOpMode {
// Can use this exact code for a limit switch
    private TouchSensor touchSensor;
    private boolean touchSensorIsPressed = false;
    private double touchSensorValue;
    private Servo servoOne;
    private double servoOneInitPosition = 0.5;
    private double servoOnePositionOne = 0.0;
    private double servoOnePositionTwo = 1.0;

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();

        while(!isStarted()) {
            getTouchSensor();
            touchSensorTelemetry();
        }
        waitForStart();
        while(opModeIsActive()) {
            getTouchSensor();
            touchSensorTelemetry();
            teleOpControls();
        }
    }
    public void initHardware() {
        initServoOne();
        initTouchSensor();
    }

    public void initTouchSensor() {
        touchSensor = hardwareMap.get(TouchSensor.class, "touchSensor");
    }

    public void teleOpControls() {
        if (touchSensorIsPressed) {
            servoOne.setPosition(servoOnePositionTwo);
        } else {
            servoOne.setPosition(servoOnePositionOne);
        }
    }

    public void touchSensorTelemetry() {
        telemetry.addData("touchSensorIsPressed", touchSensorIsPressed);
        telemetry.addData("touchSensorValue", "%.2f", touchSensorValue);
        telemetry.addData("servoOnePosition", "%.2f", servoOne.getPosition());
        telemetry.update();
    }

    public void initServoOne() {
        servoOne = hardwareMap.get(Servo.class, "servoOne");
        servoOne.setDirection(Servo.Direction.FORWARD);
        servoOne.setPosition(servoOneInitPosition);
    }

    public void getTouchSensor() {
        touchSensorIsPressed = touchSensor.isPressed();
        touchSensorValue = touchSensor.getValue();
    }
}
