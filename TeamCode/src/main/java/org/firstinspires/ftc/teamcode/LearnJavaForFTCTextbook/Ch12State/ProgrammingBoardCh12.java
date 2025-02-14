package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch12State;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch07Motor.ProgrammingBoardCh7;

public class ProgrammingBoardCh12 extends ProgrammingBoardCh7 {
    protected DigitalChannel touchSensor;
    protected Servo servo;
    protected ColorSensor colorSensor;
    protected DistanceSensor distanceSensor;
    protected AnalogInput pot;

    public void init(HardwareMap hwMap) {
        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
        motor = hwMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRotation = motor.getMotorType().getTicksPerRev();
        servo = hwMap.get(Servo.class, "servo");
        pot = hwMap.get(AnalogInput.class, "pot");
        colorSensor = hwMap.get(ColorSensor.class, "sensor_color_distance");
        distanceSensor = hwMap.get(DistanceSensor.class, "sensor_color_distance");
    }
    public boolean isTouchSensorPressed() {
        return !touchSensor.getState();
    }
    public void setMotorSpeed(double speed){
        motor.setPower(speed);
    }
    public double getMotorRotations(){
        return motor.getCurrentPosition() / ticksPerRotation;
    }
    public void setServoPosition(double position){
        servo.setPosition(position);
    }
    public double getPotAngle(){
        return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);
    }
    public int getAmountRed(){
        return colorSensor.red();
    }
    public double getDistance(DistanceUnit du){
        return distanceSensor.getDistance(du);
    }
}