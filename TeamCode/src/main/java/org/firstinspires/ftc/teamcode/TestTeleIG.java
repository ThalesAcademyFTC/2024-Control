package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TestTeleIG")
public class TestTeleIG extends OpMode {

    Goldfish robot;

    int slidePos = 1; //Basket Position
    boolean buttonPressed = false;

    double rbtSpd = 1.75;

    @Override
    public void init() {


        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);

    }

    double start = 0.5;

    @Override
    public void loop() {

        telemetry.addData("basketServo", robot.basketServo.getPosition());
        telemetry.addData("servoMoveArm", robot.clawMoveServo.getPosition());
        telemetry.addData("slidePos", slidePos);
        telemetry.addData("buttonPressed?", buttonPressed);
        telemetry.addData("Motor 1 Current Pos", robot.slideMotor.getCurrentPosition());
        telemetry.addData("Motor 2 Current Pos", robot.slideMotor2.getCurrentPosition());
        telemetry.addData("Motor 1 Target Pos", robot.slideMotor.getTargetPosition());
        telemetry.addData("Motor 2 Target Pos", robot.slideMotor2.getTargetPosition());



        double y = (-gamepad1.left_stick_y / rbtSpd);
        double x = (gamepad1.left_stick_x / rbtSpd);
        double turn = (gamepad1.right_stick_x / rbtSpd);

        int slidePos = 1;
        int basketPos = 0;
        boolean buttonPressed = false;

        // Move the robot using the transformed inputs
        robot.move(x, y, turn);


                    //Slide motor code below//
        if (gamepad2.y && slidePos == 1) {
            slidePos += 1;
            //robot.lowSlideBucket();
        } else if (gamepad2.y && slidePos == 2) {
            slidePos += 1;
            //robot.highSlideBucket();
        } else {
        }

        if (gamepad2.a && slidePos == 3) {
            slidePos -= 1;
            //robot.lowSlideBucket();
        } else if (gamepad2.a && slidePos == 2) {
            slidePos -= 1;
            //robot.resetSlide();
        } else { }


        telemetry.update();
    }
}