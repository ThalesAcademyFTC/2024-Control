package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class lowBarRightAuton extends LinearOpMode {

    public Goldfish robot;
    public ElapsedTime runtime = new ElapsedTime();


    public void runOpMode() {

        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);

        double speed = 0.7;
        long rest = 50;

        robot.basketDown();
        robot.armReset();

        waitForStart();

        //IMPORTANT!!!
        //BUCKET ON THE ROBOT SHOULD BE FACING THE TEAM ALLIANCE
        //AND SPECIMEN SHOULD BE PLACED WITH THE HOOK POINTING INTO THE ROBOT.

        /*
        The path for this auton starts on the right position of the possible starting places.
        The robot will have a specimen preloaded on the claw, and the bucket will be tilted into the robot.
        The intended path is for the robot to place a specimen on the low bar and push the 3 samples
        of our alliance into the observation zone in preparation for the teleop round. In the end, the robot
        will park in the observation zone.
        */

        //Robot moves to the low bar
        robot.moveForwardInches(23, speed);

        //Robot clips a specimen on the low bar
        robot.setArmTicks(-1700, 1);
        robot.waitForArmMotor();
        robot.openClaw();
        robot.moveBackwardInches(3, speed);
        robot.armReset();
        robot.waitForArmMotor();
        robot.closeClaw();

        //Robot moves over to the three alliance samples
        robot.moveRightInches(36, speed);
        robot.moveForwardInches(23, speed);

        //robot opens claw and moves towards the sample
        robot.openClaw();
        robot.moveRightInches(8, speed);

        //robot picks up sample
        robot.closeClaw();

        //robot moves sample to basket
        robot.armDump();
        robot.basketDown();
        robot.waitForArmMotor();

        //robot returns to observation zone with a sample
        robot.moveBackwardInches(47, speed);
        robot.moveRightInches(21, speed);

        /* sample will be used in teleop period for scoring on the bucket (may change depending on
        alliance members' strategy) */


    }

}
