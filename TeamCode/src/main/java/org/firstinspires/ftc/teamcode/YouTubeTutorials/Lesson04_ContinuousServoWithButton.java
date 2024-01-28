package org.firstinspires.ftc.teamcode.YouTubeTutorials;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(group = "Tutorial")
public class Lesson04_ContinuousServoWithButton extends LinearOpMode {
    private CRServo crServo;
    private double crServoZeroPower = 0.0;
    private double crServoPower = 1.0;


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
        initCRServo();
    }


    public void initCRServo() {
        crServo = hardwareMap.get(CRServo.class, "crServo");
        crServo.setDirection(CRServo.Direction.FORWARD);
        crServo.setPower(crServoZeroPower);
    }


    public void servoTelemetry() {
        telemetry.addData("CRServo Power", crServo.getPower());
        telemetry.addData("CRServo Direction", crServo.getDirection());
        telemetry.update();
    }


    public void teleOpControls() {
        if (gamepad1.dpad_left) {
            crServo.setPower((-1 * crServoPower));
        }
        if (gamepad1.dpad_right) {
            crServo.setPower((crServoPower));
        }
        if (gamepad1.dpad_down) {
            crServo.setPower((crServoZeroPower));
        }
    }
}

