package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;
import android.text.TextUtils;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Record", group="Test")

public class Record extends LinearOpMode {
    ArrayList<String> steps = new ArrayList<String>();
    String currentType = "";
    long currentStart = 0;

    public void runOpMode () {
        DriveTrain dt = new DriveTrain(hardwareMap);
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
            } else if (currentStart != 0) {
                recordStep("S", 0);
                dt.setPower(0);
            }
            telemetry.addData("Steps", TextUtils.join(" ", steps));
            telemetry.update();
        }
    }

    public void recordStep (String type, int magnitude) {
        String typeStr;
        if (magnitude == 0) {
            typeStr = type;
        } else {
            typeStr = type + Integer.toString(magnitude);
        }
        if (!currentType.equals(typeStr)) {
            if (!currentType.equals("")) {
                String diff = Long.toString(System.currentTimeMillis() - currentStart);
                steps.add(currentType + "," + diff);
            }
            currentStart = System.currentTimeMillis();
            currentType = typeStr;
        }
    }
}
