package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch20DriveTrain;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class FieldRelativeMecanumDrivePolarCoords extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    IMU imu;

    @Override
    public void init() {
        drive.init(hardwareMap);

        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(RevOrientation));
    }

    private void driveFieldRelative(double forward, double right, double rotate) {
        double robotAngle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // convert this to polar coordinates
        double theta = Math.atan2(forward, right); // Angle from polar axis, (1, 0, 0), theta = 90
        double r = Math.hypot(forward, right); // This is just the magnitude of the polar vector line thingy :)

        // rotate angle
        theta = AngleUnit.normalizeRadians(theta - robotAngle); // robot's at a 30 degree angle, 60

        // covert back to cartesian
        double newForward = r * Math.sin(theta); // y value :D
        double newRight = r * Math.cos(theta); // x value :)

        drive.drive(newForward, newRight, rotate);
    }

    @ Override
    public void loop() {
        double forward = -gamepad1.left_stick_y; // y is reversed (so know up = up)
        double right = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        driveFieldRelative(forward, right, rotate);
    }
}
