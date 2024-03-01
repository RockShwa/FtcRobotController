package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch12State;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Make a program that ramps your motor to full speed (.25 for 250ms, .50
 * for 250ms, .75 for 250ms, 1.0) and goes at full speed until the touch
 * sensor is pressed.
 * 2. Make a program that turns the motor until the distance sensor is less
 * than 10cm OR 5 seconds has passed and then turns the servo.
 */

public class TeleOpCh12 extends OpMode {
    enum MotorState {
        START,
        QUARTER,
        HALF,
        THREE_QUARTERS,
        FULL,
        DONE
    }

    enum ServoState {
        START,
        GO_UNTIL_DISTANCE,
        TURN_SERVO,
        DONE
    }
    ProgrammingBoardCh12 board = new ProgrammingBoardCh12();
    MotorState motorState = MotorState.START;
    ServoState servoState = ServoState.START;
    double lastStepTime;

    public void init() {
        board.init(hardwareMap);
    }

    public void start() {
        motorState = MotorState.START;
        servoState = servoState.START;
    }

    public void loop() {
        telemetry.addData("Motor State", motorState);
        telemetry.addData("Servo State", servoState);
        telemetry.addData("Runtime", getRuntime()); // In seconds

        switchStateMotor();
        switchStateServo();
    }
    public void switchStateServo() {
        switch (servoState) {
            case START:
                board.setMotorSpeed(0.5);
                board.setServoPosition(0.0);
                resetRuntime();
                servoState = ServoState.GO_UNTIL_DISTANCE;
                break;
            case GO_UNTIL_DISTANCE:
                if((board.getDistance(DistanceUnit.CM) < 10) || (getRuntime() > 5)) {
                    board.setMotorSpeed(0.0);
                    servoState = ServoState.TURN_SERVO;
                }
                break;
            case TURN_SERVO:
                board.setServoPosition(0.5);
                servoState = ServoState.DONE;
                break;
            default:
                telemetry.addData("Auto", "Finished");
        }
    }
    public void switchStateMotor() {
        switch(motorState) {
            case START:
                board.setMotorSpeed(0.25);
                motorState = MotorState.QUARTER;
                lastStepTime = getRuntime();
                break;
            case QUARTER:
                if (getRuntime() > lastStepTime + .250) {
                    board.setMotorSpeed(0.5);
                    motorState = MotorState.HALF;
                    lastStepTime = getRuntime();
                }
                break;
            case HALF:
                if (getRuntime() > lastStepTime + .250) {
                    board.setMotorSpeed(0.75);
                    motorState = MotorState.THREE_QUARTERS;
                    lastStepTime = getRuntime();
                }
                break;
            case THREE_QUARTERS:
                if (getRuntime() > lastStepTime + .250) {
                    board.setMotorSpeed(1);
                    motorState = MotorState.FULL;
                    lastStepTime = getRuntime();
                }
                break;
            case FULL:
                if (board.isTouchSensorPressed()) {
                    board.setMotorSpeed(0.0);
                    motorState = MotorState.DONE;
                }
                break;
            default:
                telemetry.addData("Auto", "Finished");

        }
    }


}
