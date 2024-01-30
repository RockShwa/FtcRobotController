package org.firstinspires.ftc.teamcode.YouTubeTutorials;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@Disabled
@TeleOp(group = "Tutorials")
public class Lesson16_VoltageSensor extends LinearOpMode {
    private VoltageSensor voltageSensor;
    private double voltageSensorThreshold = 9.0;
    private DcMotor motorOne;
    private double motorOneZeroPower = 0.0;
    private double motorOnePower = 1.0;

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while(!isStarted()) {
            sensorTelemetry();
        }
        waitForStart();
        while(opModeIsActive()) {
            sensorTelemetry();
            teleOpControls();
        }
    }
    public void initHardware() {
        initVoltageSensor();
        initMotorOne();
    }

    public void initVoltageSensor() {
        voltageSensor = hardwareMap.voltageSensor.iterator().next(); //don't need a config file since it's built into control hub
    }

    public void initMotorOne() {
        motorOne = hardwareMap.get(DcMotor.class, "motorOne");
        motorOne.setDirection(DcMotor.Direction.FORWARD);
        motorOne.setPower(motorOneZeroPower);
        motorOne.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void sensorTelemetry() {
        telemetry.addData("getVoltage", voltageSensor.getVoltage());
        telemetry.update();
    }

    public void teleOpControls() {
        if (gamepad2.x) {
            motorOne.setPower((-motorOnePower));
        }
        if (gamepad2.a) {
            motorOne.setPower(motorOneZeroPower);
        }
        if (gamepad2.b) {
            motorOne.setPower(motorOnePower);
        }
        if ((motorOne.getPower() == motorOnePower) && (voltageSensor.getVoltage() <= voltageSensorThreshold)) {
            motorOne.setPower(-motorOnePower); // if something gets stuck in intake system and voltage drops, robot will respond by reversing motor direction and hopefully pushing the thing back out
        }

    }
}
