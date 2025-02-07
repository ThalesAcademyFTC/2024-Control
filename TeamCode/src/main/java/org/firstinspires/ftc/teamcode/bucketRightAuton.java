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
        long rest = 50;

        robot.basketDown();
        robot.armDump();

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

        //Arm extends
        robot.armReset();

        //Robot moves to the low bar
        robot.moveForwardInches(27, speed);
        robot.armCollect();
        robot.clawMoveServo.setPosition(0.4);
        robot.waitForArmMotor();
        sleep(rest);

        //Robot clips a specimen on the low bar


        //Robot moves over to the three alliance samples
        robot.moveBackwardInches(3, speed);
        robot.moveRightInches(36, speed);
        robot.moveForwardInches(23, speed);
        robot.openClaw();
        robot.moveRightInches(8, speed);


        //robot picks up sample
        robot.closeClaw();
        robot.moveBackwardInches(47, speed);



    }

}
