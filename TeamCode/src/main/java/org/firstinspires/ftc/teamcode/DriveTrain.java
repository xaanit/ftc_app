package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

class DriveTrain {
    private final DcMotor dtLeft;
    private final DcMotor dtRight;
    DriveTrain (DcMotor dtLeft, DcMotor dtRight, DcMotor.RunMode mode) {
        dtLeft.setMode(mode);
        dtLeft.setDirection(DcMotor.Direction.FORWARD);
        dtRight.setDirection(DcMotor.Direction.REVERSE);
        this.dtLeft = dtLeft;
        this.dtRight = dtRight;
    }
    void setTargetPosition (int position) {
        this.dtLeft.setTargetPosition(position);
        this.dtRight.setTargetPosition(position);
    }
    void setPower (double power) {
        this.dtLeft.setPower(power);
        this.dtRight.setPower(power);
    }
    boolean isBusy () {
        return this.dtLeft.isBusy() || this.dtRight.isBusy();
    }
}
