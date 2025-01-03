package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Teleop")
public class Teleop extends OpMode {

    Goldfish robot;

    int basketPos = 1; //Basket Position
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
        telemetry.addData("slidePos", basketPos);
        telemetry.update();


        double y = (-gamepad1.left_stick_y / rbtSpd);
        double x = (gamepad1.left_stick_x / rbtSpd);
        double turn = (gamepad1.right_stick_x / rbtSpd);


        //robot.move(x, y, turn);
        robot.move(x, y, turn);

        if (gamepad2.right_trigger > 0.1) {
            robot.openClaw();
        } else if (gamepad2.left_trigger > 0.1) {
            robot.closeClaw();
        }

        /*
        if (robot.isColor("green")) {
            robot.moveForward(0.5);
        }
        */


        if (gamepad2.right_stick_y > 0.25) {
            robot.lowerSlide();
        } else if (gamepad2.right_stick_y < -0.25) {
            robot.liftSlide();
        } else {
            robot.slideMotor.setPower(0);
            robot.slideMotor2.setPower(0);
        }

        if (gamepad2.left_stick_y > .25) {
            robot.armAwayBasket();
        } else if (gamepad2.left_stick_y < -.25) {
            robot.armToBasket();
        } else {
            robot.armMotor.setPower(0);
        }



        if (gamepad2.y && buttonPressed == false && basketPos < 3) {
            buttonPressed = true;
            basketPos += 1;

            if (basketPos == 3) {
                robot.highSlideBucket();
            } else if (basketPos == 2) {
                robot.lowSlideBucket();
            } else if (basketPos == 1) {
                robot.resetSlide();
            }
        } else {
            buttonPressed = false;
        }


        if (gamepad2.a && buttonPressed == false && basketPos > 1) {
            buttonPressed = true;
            basketPos -= 1;

            if (basketPos == 3) {
                robot.highSlideBucket();
            } else if (basketPos == 2) {
                robot.lowSlideBucket();
            } else if (basketPos == 1) {
                robot.resetSlide();
            }
        } else {
            buttonPressed = false;
        }

/*
                if (gamepad2.y) {
                    robot.basketServo.setPosition(.85);
                }
                if (gamepad2.b) {
                    robot.basketServo.setPosition(.6);
                }
                if (gamepad2.a) {
                    robot.basketServo.setPosition(.45);
                }
*/
        if (gamepad2.dpad_down) {
            robot.clawMoveServo.setPosition(.25);
        }

        if (gamepad2.dpad_up) {
            robot.clawMoveServo.setPosition(1);
        }

        if (gamepad2.left_stick_x == 0 && gamepad1.left_stick_y == 0) {
            robot.move(0, 0, 0);
        }

        if (gamepad1.dpad_down) {
            start -= 0.01;
            robot.clawMoveServo.setPosition(start);
        }

        if (gamepad1.dpad_up) {
            start += 0.01;
            robot.clawMoveServo.setPosition(start);
        }
        if (gamepad2.left_bumper) {
            robot.setSuspensionServo(.15);
        } else if (gamepad2.right_bumper) {
            robot.setSuspensionServo(.8);
        }
        telemetry.update();
    }
}


/* I wish i was a little bit taller i wish i was a baller i wish i had a girl who looked good i would call her
i wish i had a rabbit in a hat and a bat and a '64 impala 
i wish i was like 6' 9" so i could get it with yoshi and she dont know me but yo she's really fine
and i know shes living fast, her boyfriend's tall and he plays ball so how'm i gonna compete with that
cause when it comes to playin basketball im always last to be picked and in some cases never picked at all
so i just sit up on the wall, or sit up in the bleachers with the rest of the girls who came to watch they man ball
dag yall i never understood black why the jocks get the fly girls and me i get the hood rats
i tell em scat skiddle skebabble got hit with a bottle and put in the hospital for talking that mess
i confess its a shame when you living in a city thats the size of a box and nobody knows yo name
glad i came- to my senses like quick quick got sick sick to my stomach
overcometh by thoughts of me and her together right? so when i asked her out she says i wasnt her type */