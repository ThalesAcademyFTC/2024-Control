package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleopFieldCentric")
public class TeleopFieldCentric extends OpMode {

    Goldfish robot;

    int basketPos = 1; //Basket Position
    boolean buttonPressed = false;

    enum SlidePosition {
        RESET,
        LOW,
        HIGH
    }

    SlidePosition slidePos = SlidePosition.RESET;

    int ready = 0;

    double rbtSpd = 1.2;

    @Override
    public void init() {


        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);

        telemetry.addData("Slide Position", robot.slideMotor.getCurrentPosition());
        telemetry.addData("Slide Position 2", robot.slideMotor2.getCurrentPosition());
        telemetry.addData("basketServo", robot.basketServo.getPosition());
        //telemetry.addData("servoMoveArm", robot.clawMoveServo.getPosition());
        telemetry.addData("slidePos", basketPos);
        telemetry.addData("armPos", robot.armMotor.getCurrentPosition());
        telemetry.addData("Slide Velocity", robot.slideMotor.getVelocity());

        telemetry.addData("slide motor 1", "%7d / % 7d", robot.slideMotor.getCurrentPosition(), robot.slideMotor.getTargetPosition());
        telemetry.addData("slide motor 2", "%7d / % 7d", robot.slideMotor2.getCurrentPosition(), robot.slideMotor2.getTargetPosition());


    }

    @Override
    public void loop() {
        telemetry.update();


        }

    }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              //Rest in peace Billy Dignam </3