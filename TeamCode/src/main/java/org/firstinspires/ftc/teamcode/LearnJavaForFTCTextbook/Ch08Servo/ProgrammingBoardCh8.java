package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch08Servo;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * 1. Change the ProgrammingBoard class so that the servo is backwards and
 *      only goes from the midpoint to far left.
 */
public class ProgrammingBoardCh8 {
    private Servo servo;

    public void init(HardwareMap hwMap) {
        servo = hwMap.get(Servo.class, "servo");
        servo.setDirection(Servo.Direction.REVERSE);
        servo.scaleRange(0.5, 1.0); // Midpoint to far left restriction
    }
    public void setServoPosition(double position){
        servo.setPosition(position);
    }
}
