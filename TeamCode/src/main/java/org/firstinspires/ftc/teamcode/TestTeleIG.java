package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TestTeleIG")
public class TestTeleIG extends OpMode {

    Goldfish robot;

    enum SlidePosition {
        RESET,
        LOW,
        HIGH
    }



    SlidePosition slidePos = SlidePosition.RESET;


    boolean buttonPressed = false;

    double rbtSpd = 0.75;

    @Override
    public void init() {


        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);

    }

    double start = 0.5;

    @Override

    public void loop() {



        double y = (-gamepad1.left_stick_y * rbtSpd);
        double x = (gamepad1.left_stick_x * rbtSpd);
        double turn = (gamepad1.right_stick_x * rbtSpd);


        // Slide motor code below
        if (gamepad2.a) {
            rbtSpd = 0.25;
        }
        if (gamepad2.b) {
            rbtSpd = 0.5;
        }
        if (gamepad2.x) {
            rbtSpd = 0.75;
        }
        if (gamepad2.y) {
            rbtSpd = 1;
        }



        telemetry.update();
    }
}