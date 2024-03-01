package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch09AnalogSensors;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Make a class method for your ProgrammingBoard that exposes the pot in
 * the range [0.0..1.0]
 */
public class ProgrammingBoardCh9 {
    private AnalogInput pot;
    private Servo servo;

    public void init(HardwareMap hwMap) {
        pot = hwMap.get(AnalogInput.class, "pot");
        servo = hwMap.get(Servo.class, "servo");
    }
    public double getPotAngle(){
        return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);
    }

    public void setServoPosition(double position) {
        servo.setPosition(position);
    }

    public double getPotRange() {
        return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 1.0);
        // This basically just takes the voltage, and sees its relationship between 0 and max voltage,
        // and then translates that relationship to between 0 and 1
        // For example: Range.scale(25, 0, 100, 0, 1) -> Figures out that 25 is 1/4 of the wat btwn 0 and 100,
        // so it would return 0.25 because that's 1/4 of the way btwn 0 and 1
    }

}
