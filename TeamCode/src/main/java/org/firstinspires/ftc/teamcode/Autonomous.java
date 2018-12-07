package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous (name="Auto Test", group="JJ")

public class Autonomous extends LinearOpMode {
    public void runOpMode () {
        DcMotor dtLeft = hardwareMap.get(DcMotor.class, "dt left");
        DcMotor dtRight = hardwareMap.get(DcMotor.class, "dt right");

        DriveTrain dt = new DriveTrain(dtLeft, dtRight, DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        dt.setTargetPosition(1000);
        dt.setPower(0.2d);
        while (dt.isBusy()) {
            // debug
            telemetry.addData("L", dtLeft.getCurrentPosition());
            telemetry.addData("R", dtRight.getCurrentPosition());
            telemetry.update();
        }
        dt.setTurn(0.2d);
        dt.setTargetPosition(1000);
        while (dt.isBusy()) {
            // debug
            telemetry.addData("L", dtLeft.getCurrentPosition());
            telemetry.addData("R", dtRight.getCurrentPosition());
            telemetry.update();
        }
    }
}
