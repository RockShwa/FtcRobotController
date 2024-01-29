package org.firstinspires.ftc.teamcode.YouTubeTutorials;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@TeleOp(group = "Tutorials")
public class Lesson06_DcMotorWithButtons extends LinearOpMode {

    private DcMotor motorOne;
    private double motorOneZeroPower = 0.0;
    private double motorOnePower = 1.0;

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while(!isStarted()) {
            motorTelemetry();
        }
        waitForStart();
        while(opModeIsActive()) {
            teleOpControls();
            motorTelemetry();
        }
    }

    public void initHardware() {
        initMotorOne();
    }

    public void initMotorOne() {
        motorOne = hardwareMap.get(DcMotor.class, "motorOne");
        motorOne.setDirection((DcMotor.Direction.FORWARD));
        motorOne.setPower(motorOneZeroPower);
        motorOne.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //uses opposite power to "brake"
        motorOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // encoder data STILL COLLECTED, but velocity of motor is set to specified power level without PID
    }
    public void motorTelemetry() {
        // 2d = 2 decimal places (double format), 2f = 2 decimal places (float format)
        telemetry.addData("motorOne", "Encoder: %2d, Power: %.2f", motorOne.getCurrentPosition(), motorOne.getPower());
        telemetry.update();
    }

    public void teleOpControls() {
        if (gamepad2.x) {
            motorOne.setPower((-1 * motorOnePower)); //backwards
        }
        if (gamepad2.a) {
            motorOne.setPower(motorOneZeroPower); //zero, duh ;)
        }
        if (gamepad2.b) {
            motorOne.setPower(motorOnePower); //forwards
        }
    }


}
