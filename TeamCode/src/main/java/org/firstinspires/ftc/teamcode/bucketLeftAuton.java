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

        double speed = 1;
        long rest = 50;

        robot.basketDown();
        robot.openClaw();
        sleep(200);
        robot.clawMoveServo.setPosition(0.6);
        sleep(200);
        robot.armReset();

        waitForStart();

        //IMPORTANT!!!
        //BUCKET ON THE ROBOT SHOULD BE FACING THE PLAYING FIELD BUCKET



        //Arm and basket extend out
        robot.armReset();
        robot.basketRest();

        //Moves to bucket
        robot.highSlideBucket();
        robot.moveDiagonalSW(26, speed);
        robot.turnLeftDegrees(45, speed);

        //Robot prepares to dump a sample
        robot.waitForSlideMotors();
        robot.moveBackwardInches(9, speed*0.5);
        sleep(200);


        //Robot dumps a sample
        robot.basketUp();

        //Robot backs up and limbs reset
        robot.moveForwardInches(3,speed);
        robot.resetSlide();
        robot.basketDown();
        sleep(rest);

        //SECOND CYCLE
        //Robot turns and moves to the middle sample

        robot.armCollect();
        robot.turnLeftDegrees(45,speed);
        robot.moveLeftInches(3,speed);
        robot.moveForwardInches(7.25,speed*0.75);

        //Robot picks up middle sample
        robot.moveRightInches(2, speed);
        robot.closeClaw();
        robot.moveRightInches(3,speed);

        //Robot returns to the bucket while placing sample in the basket
        robot.armDump();
        robot.moveBackwardInches(5,speed);
        robot.waitForArmMotor();
        robot.openClaw();
        sleep(rest*2);
        robot.clawMoveServo.setPosition(0.6);
        sleep(200);
        robot.armReset();
        sleep(100);
        robot.basketRest();
        sleep(100);

        //dump 2
        robot.highSlideBucket();
        robot.moveBackwardInches(5,speed);
        robot.turnRightDegrees(45,speed);
        robot.waitForSlideMotors();

        //Robot prepares to dump a sample
        robot.moveBackwardInches(3, speed*0.75);

        //Robot places a sample
        robot.basketUp();
        sleep(400);
        robot.basketDown();
        robot.moveForwardInches(3, speed);
        robot.resetSlide();
        robot.turnRightDegrees(45, speed);

        //Robot parks
        //Robot can park in two places. COMMENT OUT whichever one you don't need.

        //robot parks in the inspection zone
        robot.moveDiagonalNW(24, speed);
        robot.moveLeftInches(12, speed);
        robot.moveForwardInches(5, speed);

        //robot parks near the sample prison
        /*robot.moveDiagonalNE(32, speed);
        robot.moveForwardInches(24, speed);
        robot.moveRightInches(8, speed);*/


    }

}
