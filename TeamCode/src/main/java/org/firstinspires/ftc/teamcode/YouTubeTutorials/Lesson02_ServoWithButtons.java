package org.firstinspires.ftc.teamcode.YouTubeTutorials;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(group = "Tutorials")
public class Lesson02_ServoWithButtons extends LinearOpMode {
    private Servo servoOne;
    private double servoOneInitPosition = 0.5;
    private double servoOnePositionOne = 0.0;
    private double servoOnePositionTwo = 1.0;
    private int servoOneDelay = 10; //Not recommended in TeleOp


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
        initServoOne();
    }


    public void initServoOne() {
        servoOne = hardwareMap.get(Servo.class, "servoOne");
        servoOne.setDirection(Servo.Direction.FORWARD);
        servoOne.setPosition(servoOneInitPosition);
    }


    public void servoTelemetry() {
        telemetry.addData("Position", servoOne.getPosition()); //displays pwm signal/position
        telemetry.addData("Direction", servoOne.getDirection());
        telemetry.update();
    }


    public void teleOpControls() {
        if (gamepad1.a) {
            servoOne.setPosition((servoOnePositionOne));
        }
        if (gamepad1.b) {
            servoOne.setPosition((servoOnePositionTwo));
        }
        if (gamepad1.right_bumper) {
            servoOneSlower(servoOnePositionOne, servoOnePositionTwo, servoOneDelay);
        }
    }


    public void servoOneSlower(double startPosition, double endPosition, int delay) { //ONLY DO THIS IN AUTONOMOUS
        double range = ((endPosition - startPosition) * 100); //how many reps we want to get to end position
        for (int i = 0; i <= range; i++) {
            servoOne.setPosition(startPosition);
            sleep(delay); // don't take any commands for delay time
            startPosition = startPosition + 0.1; //increments position
        }
    }


}
