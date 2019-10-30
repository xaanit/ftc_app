package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Auto Test", group="JJ")

public class Auto extends LinearOpMode {
    public void runOpMode () throws InterruptedException {
        DriveTrain dt = new DriveTrain(hardwareMap);

        String park = "P1,2000";

        waitForStart();

        dt.runSteps(park);
        dt.setPower(0);
    }
}
