package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

class DriveTrain {
    private final DcMotor[] dtMotors;
    DriveTrain (HardwareMap hardwareMap) {
        DcMotor dtLeftFront = hardwareMap.get(DcMotor.class, "dt left front");
        DcMotor dtRightFront = hardwareMap.get(DcMotor.class, "dt right front");
        DcMotor dtLeftBack = hardwareMap.get(DcMotor.class, "dt left back");
        DcMotor dtRightBack = hardwareMap.get(DcMotor.class, "dt right back");
        dtMotors = new DcMotor[] { dtLeftFront, dtRightFront, dtLeftBack, dtRightBack };

        for (int i = 0; i < dtMotors.length; i++) {
            dtMotors[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            dtMotors[i].setDirection(i % 2 == 0 ? DcMotor.Direction.FORWARD : DcMotor.Direction.REVERSE);
            dtMotors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }
    void setPower (double power) {
        for (DcMotor motor : dtMotors) {
            motor.setPower(power);
        }
    }
    void setTurn (double power) {
        for (int i = 0; i < dtMotors.length; i++) {
            dtMotors[i].setPower(Range.clip(power * (i % 2 == 0 ? -1 : 1), -1.0, 1.0));
        }
    }
    void runSteps (String stepsStr) throws InterruptedException {
        String[] steps = stepsStr.split("\\s+");
        for (String step : steps) {
            String[] parts = step.split(",");
            String type = parts[0];
            Long length = Long.parseLong(parts[1]);
            switch (type) {
                case "P1":
                    setPower(1);
                    break;
                case "T1":
                    setTurn(1);
                    break;
                case "P-1":
                    setPower(-1);
                    break;
                case "T-1":
                    setTurn(-1);
                    break;
                case "S":
                    setPower(0);
                    break;
            }
            Thread.sleep(length);
        }
    }

    void setTurn (double power, double offset) {
        for (int i = 0; i < dtMotors.length; i++) {
            dtMotors[i].setPower(Range.clip(power * (i % 2 == 0 ? -1 : 1) + offset, -1.0, 1.0));
        }
    }
}
