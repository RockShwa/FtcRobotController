package org.firstinspires.ftc.teamcode.YouTubeTutorials;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(group = "Tutorials")
public class Lesson03_ServoWithJoystick extends LinearOpMode {
    private Servo servoTwo;
    private double servoTwoInitPosition = 0.5;
    private double servoTwoSensitivity = 0.75;


    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while(!isStarted()) {
            servoTelemetry();
        }
        waitForStart();
        while(opModeIsActive()) {
            teleOpControls();
            servoTelemetry();
        }


    }


    public void initHardware() {
        initServoTwo();
    }


    public void initServoTwo() {
        servoTwo = hardwareMap.get(Servo.class, "servoTwo");
        servoTwo.setDirection(Servo.Direction.REVERSE);
        servoTwo.setPosition(servoTwoInitPosition);
    }


    public void servoTelemetry() {
        telemetry.addData("Joystick Servo Position", servoTwo.getPosition()); //displays pwm signal/position
        telemetry.addData("Joystick Servo Direction", servoTwo.getDirection());
        telemetry.update();
    }


    public void teleOpControls() {
        // Backward joystick = positive values; forward vice versa (in FORWARD mode)
        // Sensitivity slows servo down
        // This takes init position and adds the right stick's y values to the position
        servoTwo.setPosition(servoTwoInitPosition + (gamepad1.right_stick_y * servoTwoSensitivity));




    }


}

