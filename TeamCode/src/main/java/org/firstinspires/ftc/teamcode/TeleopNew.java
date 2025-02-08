package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Teleop")
public class TeleopNew extends OpMode {

    Goldfish robot;
    boolean buttonPressed = false;
    boolean buttonPressed2 = false;

    enum SlidePosition {
        RESET,
        LOW,
        HIGH
    }

    SlidePosition slidePos = SlidePosition.RESET;

    enum BasketPosition {
        REST,
        PICKUP,
        DROP
    }

    BasketPosition basketPos = BasketPosition.REST;


    int bucket = 1;

    int ready = 0;

    double rbtSpd = 1.2;

    @Override
    public void init() {


        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);

        telemetry.addData("Slide Position", robot.slideMotor.getCurrentPosition());
        telemetry.addData("Slide Position 2", robot.slideMotor2.getCurrentPosition());


    }

    @Override
    public void loop() {

        telemetry.addData("basketServo", robot.basketServo.getPosition());
        telemetry.addData("servoMoveArm", robot.clawMoveServo.getPosition());
        telemetry.addData("slidePos", basketPos);
        telemetry.addData("armPos", robot.armMotor.getCurrentPosition());
        telemetry.addData("Slide Velocity", robot.slideMotor.getVelocity());

        telemetry.addData("slide motor 1", "%7d / % 7d", robot.slideMotor.getCurrentPosition(), robot.slideMotor.getTargetPosition());
        telemetry.addData("slide motor 2", "%7d / % 7d", robot.slideMotor2.getCurrentPosition(), robot.slideMotor2.getTargetPosition());
        telemetry.update();

/*
Right bumper / Left bumper || open / close claw

 */

        double y = (-gamepad1.left_stick_y / rbtSpd);
        double x = (gamepad1.left_stick_x / rbtSpd);
        double turn = (gamepad1.right_stick_x / rbtSpd);

        // Move the robot using the transformed inputs
        robot.move(x, y, turn);

        if (gamepad2.right_trigger > 0.3) {
            robot.closeClaw();
        } else if (gamepad2.left_trigger > 0.3) {
            robot.openClaw();
        }

        if (gamepad2.left_stick_y > .25) {
            robot.armToBasket();
        } else if (gamepad2.left_stick_y < -.25) {
            robot.armAwayBasket();
        } else {
            robot.armMotor.setPower(0);
        }

        if (robot.armMotor.getCurrentPosition() >= -500) {
            robot.clawMoveServo.setPosition(0.85);
        } else robot.clawMoveServo.setPosition(0.62);

        if (robot.armMotor.getCurrentPosition() >= -150)
            robot.basketDown();
            basketPos = BasketPosition.PICKUP;


        if (gamepad2.dpad_up && gamepad1.dpad_up) {
            robot.setSuspensionServo(0);
        } else if (gamepad2.dpad_down && gamepad1.dpad_down) {
            robot.setSuspensionServo(.75);
        }


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

        if (gamepad2.a && slidePos == SlidePosition.HIGH && buttonPressed == false) {
            slidePos = SlidePosition.LOW;
            buttonPressed = true;
            robot.lowSlideBucket();
        } else if (gamepad2.a && slidePos == SlidePosition.LOW && buttonPressed == false) {
            slidePos = SlidePosition.RESET;
            buttonPressed = true;
            robot.resetSlide();
        }

        if (!gamepad2.y && !gamepad2.a){
            buttonPressed = false;
        }

        // basket

        if (gamepad2.left_bumper && basketPos == BasketPosition.REST) {
            basketPos = BasketPosition.PICKUP;
            robot.basketDown();
        } else if (gamepad2.left_bumper && basketPos == BasketPosition.PICKUP) {
            basketPos = BasketPosition.REST;
            robot.basketRest();
        } else if (gamepad2.left_bumper && basketPos == BasketPosition.DROP) {
            basketPos = BasketPosition.REST;
            robot.basketRest();
        }

        if (gamepad2.right_bumper) {
            robot.basketUp();
            basketPos = BasketPosition.DROP;

        } else if (!gamepad2.right_bumper && basketPos == BasketPosition.REST || !gamepad2.right_bumper && basketPos == BasketPosition.PICKUP) {
                robot.basketRest();
            basketPos = BasketPosition.REST;
        }


        telemetry.update();
    }
}



                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    //Rest in peace Billy Dignam </3