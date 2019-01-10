package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Auto Test", group="JJ")

public class Auto extends LinearOpMode {
    public void runOpMode () throws InterruptedException {
        DriveTrain dt = new DriveTrain(hardwareMap);

        String park = "P1,911 S,159 T1,600 S,120 P1,1320 S,240 P1,360 S,121 T-1,589 S,250 P1,800 S,120 T-1,199 S,194 P1,966";

        waitForStart();

        dt.runSteps(park);
    }
}
