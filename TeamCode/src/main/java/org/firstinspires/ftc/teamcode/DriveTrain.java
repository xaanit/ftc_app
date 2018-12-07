package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

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
    void setTargetPosition (int position) {
        for (DcMotor motor : motors) {
            motor.setTargetPosition(position);
        }
    }
    void setPower (double power) {
        for (DcMotor motor : motors) {
            motor.setPower(power);
        }
    }
    void setTurn (double power) {
        for (int i = 0; i < motors.length; i++) {
            motors[i].setPower(power * (i % 2 == 0 ? -1 : 1));
        }
    }
    boolean isBusy () {
        boolean isBusy = false;
        for (DcMotor motor : motors) {
            isBusy = isBusy || motor.isBusy();
        }
        return isBusy;
    }
}
