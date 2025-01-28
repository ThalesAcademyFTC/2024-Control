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
        telemetry.addData("AY Pressed?", buttonPressed);
        telemetry.addData("Motor 1 Current Pos", robot.slideMotor.getCurrentPosition());
        telemetry.addData("Motor 2 Current Pos", robot.slideMotor2.getCurrentPosition());
        telemetry.addData("Motor 1 Target Pos", robot.slideMotor.getTargetPosition());
        telemetry.addData("Motor 2 Target Pos", robot.slideMotor2.getTargetPosition());


        double y = (-gamepad1.left_stick_y / rbtSpd);
        double x = (gamepad1.left_stick_x / rbtSpd);
        double turn = (gamepad1.right_stick_x / rbtSpd);


        // Move the robot using the transformed inputs
        robot.move(x, y, turn);


        // Slide motor code below

        if (gamepad2.y && slidePos == SlidePosition.RESET && buttonPressed == false) {
            slidePos = SlidePosition.LOW;
            buttonPressed = true;
            robot.lowSlideBucket();
        } else if (gamepad2.y && slidePos == SlidePosition.LOW && buttonPressed == false) {
            slidePos = SlidePosition.HIGH;
            buttonPressed = true;
            robot.highSlideBucket();
        }


        if (gamepad2.a && slidePos == slidePos.HIGH && buttonPressed == false) {
            slidePos = SlidePosition.LOW;
            buttonPressed = true;
            robot.lowSlideBucket();
        } else if (gamepad2.a && slidePos == SlidePosition.LOW && buttonPressed == false) {
            slidePos = SlidePosition.RESET;
            buttonPressed = true;
            robot.resetSlide();
        }

        if (!gamepad2.left_bumper && !gamepad2.right_bumper) {
            robot.basketRest();
        } else if (gamepad2.left_bumper){
            robot.basketUp();
        } else if (gamepad2.right_bumper){
            robot.basketDown();
        }

        if (gamepad2.right_trigger > 0.1) {
            robot.closeClaw();
        } else if (gamepad2.left_trigger > 0.1) {
            robot.openClaw();
        }


        if (!gamepad2.y && !gamepad2.a){
            buttonPressed = false;
        }



        telemetry.update();
    }
}