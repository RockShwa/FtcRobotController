package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch16ComputerVision;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch12State.ProgrammingBoardCh12;
import org.firstinspires.ftc.vision.VisionPortal;

public class OpModeCh16 extends OpMode {
    private FirstVisionProcessor visionProcessor;
    private VisionPortal visionPortal;

    private ProgrammingBoardCh12 board;

    @Override
    public void init() {
        visionProcessor = new FirstVisionProcessor();
        visionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), visionProcessor);
        board = new ProgrammingBoardCh12();
    }

    @Override
    public void start() {
        visionPortal.stopStreaming();
    }

    @Override
    public void loop() {
        // Will identify if it notices anything not gray and return LEFT, RIGHT, or MIDDLE
        telemetry.addData("Identified", visionProcessor.getSelection());

        switch (visionProcessor.getSelection()) {
            case NONE:
            case LEFT:
                board.setServoPosition(0.0);
                break;
            case RIGHT:
                board.setServoPosition(0.5);
                break;
            case MIDDLE:
                board.setServoPosition(1.0);
                break;
        }
    }
}
