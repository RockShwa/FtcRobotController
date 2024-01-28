package org.firstinspires.ftc.teamcode.YouTubeTutorials;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@TeleOp(group = "Tutorials")
public class Lesson012_IMUSetup extends LinearOpMode
{
    private BNO055IMU imu;
    private double imuX = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).firstAngle;
    private double imuY = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).secondAngle;
    private double imuZ = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;


    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while(!isStarted())
        {
            imuTelemetry();
        }
        waitForStart();
        while(opModeIsActive())
        {
            imuTelemetry();
        }
    }


    public void initHardware() {
        initIMU();
    }


    public void initIMU() { //Need to always do these things for IMU to work
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters(); //creates new instance
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }


    public void imuTelemetry() {
        telemetry.addData("Pitch-X", "%.2f", imuX); // USB Ports to Servo Ports
        telemetry.addData("Roll-Y", "%.2f", imuY); // Motor Ports to Sensor Ports
        telemetry.addData("Yaw-Z", "%.2f", imuZ); // Top to bottom
        telemetry.update();
    }
}


