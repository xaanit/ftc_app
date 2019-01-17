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
            double speed = gamepad1.b ? .4 : 1;
            if (gamepad1.right_stick_x != 0) {
                dt.setTurn(gamepad1.right_stick_x * speed);
            } else {
                dt.setPower(gamepad1.left_stick_y * speed);
            }
            if (gamepad1.dpad_up) {
                dt.setLift(-1 * speed);
            } else if (gamepad1.dpad_down) {
                dt.setLift(1 * speed);
            } else {
                dt.setLift(0);
            }

            int seconds = (int) runtime.seconds();
            telemetry.addData("Status", "Run Time: %dm%ds", seconds / 60, seconds % 60);
            // telemetry.addData("Motors", "left (%.2f), right (%.2f)", dtLeft.getPower(), dtRight.getPower());
            telemetry.update();
        }
    }
}
