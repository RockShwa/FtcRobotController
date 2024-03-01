package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch14Inheritance;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class TestItem {
    private String description;
    protected TestItem(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    abstract public void run(boolean on, Telemetry telemetry);
}
