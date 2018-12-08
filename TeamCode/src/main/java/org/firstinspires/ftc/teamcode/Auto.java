package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name="Auto Test", group="JJ")

public class Auto extends LinearOpMode {
    public void runOpMode () throws InterruptedException {
        DcMotor dtLeft = hardwareMap.get(DcMotor.class, "dt left");
        DcMotor dtRight = hardwareMap.get(DcMotor.class, "dt right");

        DriveTrain dt = new DriveTrain(dtLeft, dtRight, DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        dt.setPower(1);
        Thread.sleep(800L);
        dt.setTurn(1);
        Thread.sleep(388L);
        dt.setPower(1);
        Thread.sleep(2545L);
        dt.setTurn(1);
        Thread.sleep(194L);
        dt.setPower(-1);
        Thread.sleep(2000L);
    }
}
