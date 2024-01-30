package org.firstinspires.ftc.teamcode.YouTubeTutorials;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(group = "Tutorials")
public class Lesson14_REVColorSensor extends LinearOpMode {

    private ColorSensor colorSensor;
    private double redValue;
    private double greenValue;
    private double blueValue;
    private double alphaValue;
    private double targetValue = 1000;

    private Servo servoOne;
    private double servoOneInitPosition = 0.5;
    private double servoOnePositionOne = 0.0;
    private double servoOnePositionTwo = 1.0;

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();

        while(!isStarted()) {
            getColor(); //unnecessary?
            colorTelemetry();
        }
        waitForStart();
        while(opModeIsActive()) {
            getColor();
            colorTelemetry();
            teleOpControls();
        }
    }
    public void initHardware() {
        initServoOne();
        initColorSensor();
    }

    public void initColorSensor() {
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
    }

    public void getColor() {
        redValue = colorSensor.red();
        greenValue = colorSensor.green();
        blueValue = colorSensor.blue();
        alphaValue = colorSensor.alpha();
    }

    public void teleOpControls() {
        if (alphaValue > targetValue) {
            servoOne.setPosition(servoOnePositionTwo);
        } else {
            servoOne.setPosition(servoOnePositionOne);
        }
    }

    public void colorTelemetry() {
        telemetry.addData("redValue", "%2.f", redValue);
        telemetry.addData("greenValue", "%2.f", greenValue);
        telemetry.addData("blueValue", "%2.f", blueValue);
        telemetry.addData("alphaValue", "%2.f", alphaValue);
        telemetry.update();
    }

    public void initServoOne() {
        servoOne = hardwareMap.get(Servo.class, "servoOne");
        servoOne.setDirection(Servo.Direction.FORWARD);
        servoOne.setPosition(servoOneInitPosition);
    }
}
