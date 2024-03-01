package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch14Inheritance;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestDigitalChannel extends TestItem{
    private DigitalChannel digitalChannel;

    public TestDigitalChannel(String description, DigitalChannel digitalChannel) {
        super(description);
        this.digitalChannel = digitalChannel;
    }

    @Override
    public void run(boolean on, Telemetry telemetry) {
        telemetry.addData("Digital Channel State: ", digitalChannel.getState());
    }
}
