package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.DriveTrain;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous (name="Auto Test", group="JJ")

public class Autonomous extends LinearOpMode {
    public void runOpMode () {
        DcMotor dtLeft = hardwareMap.get(DcMotor.class, "dt left");
        DcMotor dtRight = hardwareMap.get(DcMotor.class, "dt right");

        dtLeft.setDirection(DcMotor.Direction.FORWARD);
        dtRight.setDirection(DcMotor.Direction.REVERSE);
        dtLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        dtRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        DriveTrain dt = new DriveTrain(dtLeft, dtRight, DcMotor.RunMode.RUN_TO_POSITION);
        dt.setTargetPosition(10);
        dt.setPower(1);
        while (dt.isBusy()) {}
        dt.setTurn(1);
        dt.setTargetPosition(10);
    }
}
