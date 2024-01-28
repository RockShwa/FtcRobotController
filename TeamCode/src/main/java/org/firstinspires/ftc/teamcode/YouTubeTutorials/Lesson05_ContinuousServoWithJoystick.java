package org.firstinspires.ftc.teamcode.YouTubeTutorials;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(group = "Tutorials")
public class Lesson05_ContinuousServoWithJoystick extends LinearOpMode {
    private CRServo crServoTwo;
    private double crServoTwoZeroPower = 0.0;
    private double crServoTwoSensitivity = 0.5;
    // sometimes when joystick is at 0,0 it still drifts and sends some signal, so a buffer helps the drift to not happen
    private double crServoTwoBuffer = 0.1;


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
        initCRServoTwo();
    }


    public void initCRServoTwo() {
        crServoTwo = hardwareMap.get(CRServo.class, "crServoTwo");
        crServoTwo.setDirection(CRServo.Direction.FORWARD);
        crServoTwo.setPower(crServoTwoZeroPower);
    }


    public void servoTelemetry() {
        telemetry.addData("CRServoTwo Power", crServoTwo.getPower());
        telemetry.addData("CRServoTwo Direction", crServoTwo.getDirection());
        telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
        telemetry.update();
    }


    public void teleOpControls() {
        // uses buffer for reason stated by instance var (mitigates effects of joystick drift)
        if (Math.abs(gamepad1.left_stick_y) >= crServoTwoSensitivity) {
            crServoTwo.setPower(gamepad1.left_stick_y + crServoTwoSensitivity);
        }
    }
}

