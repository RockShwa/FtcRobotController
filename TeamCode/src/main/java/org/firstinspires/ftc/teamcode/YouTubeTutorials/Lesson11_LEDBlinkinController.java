package org.firstinspires.ftc.teamcode.YouTubeTutorials;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp(group = "Tutorials")
public class Lesson11_LEDBlinkinController extends LinearOpMode {

    private RevBlinkinLedDriver leftLights, rightLights;
    private boolean blinkinTimer = false;
    private int blinkinDelay = 2000; // not recommended during teleop; 2000 milliseconds = 2 seconds

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while(!isStarted()) {

        }
        waitForStart();
        resetBlinkin();
        while(opModeIsActive()) {
            updateBlinkin();
        }
    }
    public void initHardware() throws InterruptedException {
        initLights();
        Thread.sleep(blinkinDelay);
        blinkinGreen();
    }

    public void initLights() {
        leftLights = hardwareMap.get(RevBlinkinLedDriver.class, "leftLights");
        rightLights = hardwareMap.get(RevBlinkinLedDriver.class, "rightLights");
        blinkinOrange();

    }

    public void resetBlinkin() {
        blinkinTimer = true;
        resetRuntime();
    }

    public void updateBlinkin() {
        if(blinkinTimer && time < 5) {
            blinkinRed();
        }
        else if (blinkinTimer && time >= 5 && time < 10) {
            blinkinGreen();
        }
        else if (blinkinTimer && time >= 10 && time < 15) {
            blinkinBlue();
        }
        else if (blinkinTimer && time >= 15 && time < 20) {
            blinkinOrange();
        }
        else {
            blinkinBlack(); // off
        }

    }

    public void blinkinRed() {
        leftLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
        rightLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
    }

    public void blinkinOrange() {
        leftLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.ORANGE);
        rightLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.ORANGE);
    }

    public void blinkinGreen() {
        leftLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
        rightLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
    }

    public void blinkinBlue() {
        leftLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
        rightLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
    }

    public void blinkinBlack() {
        leftLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
        rightLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
    }
}
