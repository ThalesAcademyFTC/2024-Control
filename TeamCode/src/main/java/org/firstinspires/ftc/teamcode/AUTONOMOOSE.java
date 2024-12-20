package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class AUTONOMOOSE extends LinearOpMode {

    public Goldfish robot;
    public ElapsedTime runtime = new ElapsedTime();

    double rest = .75;


    public void runOpMode() {

        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);

        robot.basketServo.setPosition(rest);

        waitForStart();

        robot.moveArmInches(-8, .3);
            robot.waitForArmMotor();
        robot.moveSlideInches(37, .4);
        robot.moveArmInches(-10, .3);
            robot.waitForSlideMotors(); // waiting for slide to be at topmost position
            sleep(100);
            robot.basketUp();
            sleep(500);
        robot.moveSlideInches(-37, .2);
            robot.waitForSlideMotors();



    }

}
