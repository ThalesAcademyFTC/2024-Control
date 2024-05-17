package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Teleop")
public class Teleop extends OpMode {

    Goldfish robot;

    @Override
    public void init() {

        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);

    }

    @Override
    public void loop() {

        double y = -gamepad1.left_stick_y / 2;
        double x = (gamepad1.left_stick_x) / 2;

        robot.move(x,y,0);

    }
}
