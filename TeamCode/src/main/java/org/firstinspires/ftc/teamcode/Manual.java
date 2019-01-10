package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Manual Driving", group="Driving")

public class Manual extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode () {
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        DcMotor dtLeft = hardwareMap.get(DcMotor.class, "dt left");
        DcMotor dtRight = hardwareMap.get(DcMotor.class, "dt right");
        DriveTrain dt = new DriveTrain(dtLeft, dtRight, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DcMotor lift = hardwareMap.get(DcMotor.class, "lift");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        runtime.reset();

        while (opModeIsActive()) {
            if (gamepad1.right_stick_x != 0) {
                dt.setTurn(gamepad1.right_stick_x);
            } else {
                dt.setPower(gamepad1.left_stick_y);
            }

            lift.setPower(gamepad2.left_stick_y);

            int seconds = (int) runtime.seconds();
            telemetry.addData("Status", "Run Time: %dm%ds", seconds / 60, seconds % 60);
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", dtLeft.getPower(), dtRight.getPower());
            telemetry.update();
        }
    }
}
