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

        String[] steps = "P1,911 S0,159 T1,600 S0,120 P1,1320 S0,240 P1,360 S0,121 T-1,589 S0,250 P1,800 S0,120 T-1,199 S0,194 P1,966".split("\\s+");

        waitForStart();

        for (String step : steps) {
            String[] parts = step.split(",");
            String type = parts[0];
            Long length = Long.parseLong(parts[1]);
            telemetry.addData(type, length);
            if (type == "P1") {
                dt.setPower(1);
            } else if (type == "T1") {
                dt.setTurn(1);
            } else if (type == "P-1") {
                dt.setPower(-1);
            } else if (type == "T-1") {
                dt.setTurn(-1);
            } else {
                dt.setPower(0);
            }
            telemetry.addData("Power", Double.toString(dtLeft.getPower()) + ", " + Double.toString(dtRight.getPower()));
            telemetry.update();
            Thread.sleep(length);
        }
    }
}
