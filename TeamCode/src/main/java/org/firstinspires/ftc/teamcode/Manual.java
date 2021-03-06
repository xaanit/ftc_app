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

        DriveTrain dt = new DriveTrain(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        runtime.reset();

        while (opModeIsActive()) {
            double speed = gamepad1.b ? .2 : .4;
            if (gamepad1.right_stick_x != 0) {
                dt.setTurn(gamepad1.right_stick_x * speed);
            } else {
                dt.setPower(gamepad1.left_stick_y * speed);
            }

            int seconds = (int) runtime.seconds();
            telemetry.addData("Status", "Run Time: %dm%ds", seconds / 60, seconds % 60);
            telemetry.update();
        }
    }
}
