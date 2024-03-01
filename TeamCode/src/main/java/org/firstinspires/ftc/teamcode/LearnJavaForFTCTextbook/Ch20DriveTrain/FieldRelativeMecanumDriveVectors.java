package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch20DriveTrain;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/** Questions:
 * 1) How to best organize telemetry?
 */

@TeleOp
public class FieldRelativeMecanumDriveVectors extends OpMode {
    private MecanumDrive drive;
    private IMU imu;

    @Override
    public void init() {
        drive = new MecanumDrive();
        imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);
    }

    public void loop() {

        // vector components of joystick (I think)
        double y = -gamepad1.left_stick_y; // Y stick is reversed
        double x = gamepad1.left_stick_x; // strafe
        double rx = gamepad1.right_stick_x; // rotation
        telemetry.addData("Left Joystick y (Forward): ", y);
        telemetry.addData("Left Joystick x (Strafe): ", x);
        telemetry.addData("Right Joystick (Rotate): ", rx);

        if (gamepad1.options) {
            imu.resetYaw();
        }

        driveFieldOriented(y, x, rx); // This calls drive and setPowers function in MecanumDrive btw

    }

    public void driveFieldOriented(double y, double x, double rx) {
        // IMU Heading is like the heading you're used to, 0 radians is on the y-axis
        double robotHeading = imu.getRobotYawPitchRollAngles().getYaw((AngleUnit.RADIANS));
        telemetry.addData("Robot Heading: ", robotHeading);

        // Rotate the movement direction counter to the bots rotation
        // IMU returns heading, but need to rotate robot movement counter to robots rotation, so its negative is taken (this is theta)
        // Joystick values are a vector, and rotating a vector in 2d requires this formula:
        double rotX = x * Math.cos(-robotHeading) - y * Math.sin(-robotHeading); // rotX = resultant vector's x component
        double rotY = x * Math.sin(-robotHeading) + y * Math.cos(-robotHeading); // rotY = resultant vector's y component
        telemetry.addData("rotX: ", rotX);
        telemetry.addData("rotY: ", rotY);

        rotX *= 1.1;

        drive.drive(rotY, rotX, rx);
    }



}
