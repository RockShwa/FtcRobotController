package org.firstinspires.ftc.teamcode.YouTubeTutorials;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp(group = "Tutorials")
public class Lesson01_BasicTeleOpStructure extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while(!isStarted()) {}
        waitForStart();
        while(opModeIsActive()) {}
    }
    public void initHardware() {}


}
