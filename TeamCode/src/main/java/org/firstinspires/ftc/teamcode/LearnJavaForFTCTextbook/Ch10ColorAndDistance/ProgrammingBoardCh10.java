package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch10ColorAndDistance;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch07Motor.ProgrammingBoardCh7;

/**
 * Add a method getAmountBlue()to the ProgrammingBoard and report it
 * back by changing the OpMode
 */
public class ProgrammingBoardCh10 extends ProgrammingBoardCh7 {

    private ColorSensor colorSensor;
    private DistanceSensor distanceSensor;

    public void init(HardwareMap hwMap) {
        motor = hwMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRotation = motor.getMotorType().getTicksPerRev();

        colorSensor = hwMap.get(ColorSensor.class, "colorSensor");
        distanceSensor = hwMap.get(DistanceSensor.class, "distanceSensor");
    }

    public void setMotorSpeed(double speed){
        motor.setPower(speed);
    }

    public double getMotorRotations() {
        return motor.getCurrentPosition() / ticksPerRotation;
    }

    public void changeZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        motor.setZeroPowerBehavior(zeroPowerBehavior);
    }

    public int getAmountRed() {
        return colorSensor.red();
    }

    public double getDistance(DistanceUnit du) {
        return distanceSensor.getDistance(du);
    }

    public int getAmountBlue() {
        return colorSensor.blue();
    }
}
