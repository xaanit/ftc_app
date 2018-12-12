package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name="Testing", group="Test")

public class Testing extends LinearOpMode {
    @Override
    public void runOpMode () {
        ColorSensor color = hardwareMap.get(ColorSensor.class, "color");
        waitForStart();
        while (true) {
            telemetry.addData("RGB", "%s, %s, %s", color.red(), color.green(), color.blue());
            telemetry.addData("Alpha", color.alpha());
            telemetry.addData("Hue", color.argb());
            telemetry.update();
        }
    }
}
