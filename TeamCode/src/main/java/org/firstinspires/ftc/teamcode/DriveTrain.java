package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

class DriveTrain {
    private final DcMotor[] motors;
    DriveTrain (DcMotor dtLeft, DcMotor dtRight, DcMotor.RunMode mode) {
        dtLeft.setMode(mode);
        dtRight.setMode(mode);
        dtLeft.setDirection(DcMotor.Direction.FORWARD);
        dtRight.setDirection(DcMotor.Direction.REVERSE);
        motors = new DcMotor[] { dtLeft, dtRight };
    }
    void setTargetPosition (int position) {
        for (int i = 0; i < motors.length; i++) {
            motors[i].setTargetPosition(position);
        }
    }
    void setPower (double power) {
        for (int i = 0; i < motors.length; i++) {
            motors[i].setPower(power);
        }
    }
    void setTurn (double power) {
        for (int i = 0; i < motors.length; i++) {
            motors[i].setPower(power * (i % 2 == 0 ? -1 : 1));
        }
    }
    boolean isBusy () {
        boolean isBusy = false;
        for (int i = 0; i < motors.length; i++) {
            isBusy = isBusy || motors[i].isBusy();
        }
        return isBusy;
    }
}
