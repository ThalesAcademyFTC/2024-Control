package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class bucketRightAuton extends LinearOpMode {

    public Goldfish robot;
    public ElapsedTime runtime = new ElapsedTime();


    public void runOpMode() {

        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);

        double speed = 0.7;
        long rest = 100;
        int x = 0;

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
        robot.armReset();
        robot.moveLeftInches(15,speed);
        robot.waitForArmMotor();
        robot.moveArmInches(2,speed);


    }

}
