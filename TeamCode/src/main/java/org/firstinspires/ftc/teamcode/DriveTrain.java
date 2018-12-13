package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

class DriveTrain {
    private final DcMotor[] motors;
    DriveTrain (DcMotor dtLeft, DcMotor dtRight, DcMotor.RunMode mode) {
        motors = new DcMotor[] { dtLeft, dtRight };
        for (int i = 0; i < motors.length; i++) {
            motors[i].setMode(mode);
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
}
