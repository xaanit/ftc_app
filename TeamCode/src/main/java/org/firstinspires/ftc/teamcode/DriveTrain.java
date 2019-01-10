package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

class DriveTrain {
    private final DcMotor[] motors;
    DriveTrain (HardwareMap hardwareMap) {
        DcMotor dtLeft = hardwareMap.get(DcMotor.class, "dt left");
        DcMotor dtRight = hardwareMap.get(DcMotor.class, "dt right");
        motors = new DcMotor[] { dtLeft, dtRight };

        for (int i = 0; i < motors.length; i++) {
            motors[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motors[i].setDirection(i % 2 == 0 ? DcMotor.Direction.FORWARD : DcMotor.Direction.REVERSE);
            motors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }
    void setPower (double power) {
        for (DcMotor motor : motors) {
            motor.setPower(power);
        }
    }
    void setTurn (double power) {
        for (int i = 0; i < motors.length; i++) {
            motors[i].setPower(Range.clip(power * (i % 2 == 0 ? -1 : 1), -1.0, 1.0));
        }
    }
    void setTurn (double power, double offset) {
        for (int i = 0; i < motors.length; i++) {
            motors[i].setPower(Range.clip(power * (i % 2 == 0 ? -1 : 1) + offset, -1.0, 1.0));
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
}
