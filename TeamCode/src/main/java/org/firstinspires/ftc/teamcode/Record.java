package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;
import android.text.TextUtils;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Record", group="Test")

public class Record extends LinearOpMode {
    ArrayList<String> steps = new ArrayList<String>();
    String currentType;
    long currentStart;

    public void runOpMode () {
        DcMotor dtLeft = hardwareMap.get(DcMotor.class, "dt left");
        DcMotor dtRight = hardwareMap.get(DcMotor.class, "dt right");

        DriveTrain dt = new DriveTrain(dtLeft, dtRight, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                recordStep("P", 1);
                dt.setPower(1);
            } else if (gamepad1.dpad_right) {
                recordStep("T", 1);
                dt.setTurn(1);
            } else if (gamepad1.dpad_down) {
                recordStep("P", -1);
                dt.setPower(-1);
            } else if (gamepad1.dpad_left) {
                recordStep("T", -1);
                dt.setTurn(-1);
            } else {
                recordStep("S", 0);
                dt.setPower(0);
            }
            telemetry.addData("Steps", TextUtils.join(" ", steps));
            telemetry.update();
        }
    }

    public void recordStep (String type, int magnitude) {
        if (currentType != type) {
            String diff = Long.toString(System.currentTimeMillis() - currentStart);
            currentStart = System.currentTimeMillis();
            steps.add(type + Integer.toString(magnitude) + "," + diff);
        }
    }
}
