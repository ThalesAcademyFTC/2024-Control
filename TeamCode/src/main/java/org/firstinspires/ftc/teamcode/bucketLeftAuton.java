package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class bucketLeftAuton extends LinearOpMode {

    public Goldfish robot;
    public ElapsedTime runtime = new ElapsedTime();


    public void runOpMode() {

        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);

        double speed = 0.7;
        long rest = 100;

        waitForStart();
/*
        robot.moveForwardInches(foot, speed);
        sleep(rest);
        robot.moveLeftInches(foot, speed);
        sleep(rest);
        robot.turnLeftDegrees(135, speed);
        sleep(rest);
        robot.moveForwardInches(foot, speed);
        sleep(rest);
        robot.turnLeftDegrees(45,speed);
        sleep(rest);
        robot.moveLeftInches(118, speed);
        sleep(rest);
*/

        robot.moveForwardInches(12, speed);
        sleep(rest);
        robot.lowerArm();
        sleep(rest);
        robot.openClaw();
        sleep(rest);
        robot.closeClaw();
        robot.liftArm();
        robot.moveRightInches(118, speed);
        sleep(rest);
        robot.moveBackwardInches(12, speed);

    }

}
