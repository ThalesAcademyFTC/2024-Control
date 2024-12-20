package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@Disabled
@TeleOp(name="ColorHappyYay")
public class ColorHappyYay extends OpMode {

    Goldfish robot;


    public void init() {
        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM );
    }

    @Override
    public void loop() {

        if (gamepad1.a) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
        }
        if (gamepad1.a && gamepad1.y) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.ORANGE);
        }
        if (gamepad1.y) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.YELLOW);
        }
        if (gamepad1.y && gamepad1.b) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
        }
        if (gamepad1.b) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
        }


    }

}
