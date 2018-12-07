package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Java Drivetrain", group="JJ")

public class Main extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        DcMotor dtLeft = hardwareMap.get(DcMotor.class, "dt left");
        DcMotor dtRight = hardwareMap.get(DcMotor.class, "dt right");
        DriveTrain dt = new DriveTrain(dtLeft, dtRight, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Servo clawLeft = hardwareMap.get(Servo.class, "claw left");
        Servo clawRight = hardwareMap.get(Servo.class, "claw right");

        clawLeft.setDirection(Servo.Direction.FORWARD);
        clawRight.setDirection(Servo.Direction.REVERSE);

        clawLeft.scaleRange(.65d, .8d);
        clawRight.scaleRange(0, .15d);

        clawLeft.setPosition(0);
        clawRight.setPosition(1);

        boolean xLastPressed = false;
        boolean clawGrab = false;

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        runtime.reset();

        while (opModeIsActive()) {
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double dtLeftPower = Range.clip(drive + turn, -1.0, 1.0);
            double dtRightPower = Range.clip(drive - turn, -1.0, 1.0);

            if (xLastPressed && !gamepad1.x) {
                clawGrab = !clawGrab;
            }
            xLastPressed = gamepad1.x;

            double clawLeftPos = clawGrab ? 1 : 0;
            double clawRightPos = clawGrab ? 0 : 1;

            dt.setPower(dtPower);
            dtLeft.setPower(dtLeftPower);
            dtRight.setPower(dtRightPower);
            clawLeft.setPosition(clawLeftPos);
            clawRight.setPosition(clawRightPos);

            int seconds = (int) runtime.seconds();
            telemetry.addData("Status", "Run Time: %dm%ds", seconds / 60, seconds % 60);
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", dtLeftPower, dtRightPower);
            telemetry.addData("Claws", "left (%.2f), right (%.2f)", clawLeftPos, clawRightPos);
            telemetry.update();
        }
    }
}
