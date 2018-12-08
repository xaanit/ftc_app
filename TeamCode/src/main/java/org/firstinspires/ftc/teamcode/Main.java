package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
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
        Servo clawJoint = hardwareMap.get(Servo.class, "claw joint");
        CRServo clawArm = hardwareMap.get(CRServo.class, "claw arm");

        clawLeft.setDirection(Servo.Direction.FORWARD);
        clawRight.setDirection(Servo.Direction.REVERSE);

        clawLeft.scaleRange(.65d, .8d);
        clawRight.scaleRange(0, .15d);

        clawLeft.setPosition(0);
        clawRight.setPosition(1);
        clawJoint.setPosition(0);

        boolean xLastPressed = false;
        boolean clawGrab = false;

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        runtime.reset();

        while (opModeIsActive()) {
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            dt.setTurn(drive, turn);

            if (xLastPressed && !gamepad1.x) {
                clawGrab = !clawGrab;
            }
            xLastPressed = gamepad1.x;

            clawLeft.setPosition(clawGrab ? 1 : 0);
            clawRight.setPosition(clawGrab ? 0 : 1);
            clawArm.setPower(gamepad1.);

            int seconds = (int) runtime.seconds();
            telemetry.addData("Status", "Run Time: %dm%ds", seconds / 60, seconds % 60);
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", dtLeft.getPower(), dtRight.getPower());
            telemetry.addData("Claws", "left (%.2f), right (%.2f)", clawLeft.getPosition(), clawRight.getPosition());
            telemetry.update();
        }
    }
}
