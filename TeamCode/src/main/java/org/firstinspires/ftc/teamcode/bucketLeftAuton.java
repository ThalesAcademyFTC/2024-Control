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
        //robot.clawMoveServo.setPosition(0.6);
        //sleep(200);
        robot.armReset();

        waitForStart();

        //IMPORTANT!!!
        //BUCKET ON THE ROBOT SHOULD BE FACING THE PLAYING FIELD BUCKET


        //reset to start
        robot.armReset();
        robot.basketRest();

        //Moves to bucket
        robot.highSlideBucket();
        robot.moveDiagonalSW(26, speed);
        robot.turnLeftDegrees(45, speed);
        robot.waitForSlideMotors();
        robot.moveBackwardInches(9, speed*0.5);
        sleep(200);


        //Robot dumps first sample
        robot.basketUp();
        robot.moveForwardInches(3,speed);
        robot.resetSlide();
        robot.basketDown();
        sleep(rest);

        //SECOND CYCLE

        //Robot turns and moves to the middle sample
        robot.armCollect();
        robot.turnLeftDegrees(40,speed);
        robot.moveLeftInches(5,speed);
        robot.moveForwardInches(7,speed*0.75);

        //Robot picks up middle sample
        robot.armMotor.setTargetPosition(-1705);
        //robot.clawMoveServo.setPosition(0.57);
        robot.moveRightInches(2, speed);
        robot.closeClaw();
        robot.moveRightInches(2,speed);
        robot.armDump();

        //robot moves to bucket
        robot.moveBackwardInches(5,speed);
        robot.waitForArmMotor();
        robot.openClaw();
        sleep(rest*2);
        //robot.clawMoveServo.setPosition(0.6);
        //sleep(200);
        robot.armReset();
        sleep(100);
        robot.basketRest();

        //dump second sample
        robot.highSlideBucket();
        robot.turnRightDegrees(45,speed);
        robot.moveDiagonalNW(-4,speed);
        robot.waitForSlideMotors();
        robot.moveBackwardInches(3,speed*0.75);

        //dump
        robot.basketUp();
        sleep(350);
        robot.basketDown();
        robot.moveForwardInches(3, speed);
        robot.resetSlide();
        robot.turnRightDegrees(45, speed);
        robot.armDump();

        //Robot parks
        robot.moveDiagonalNW(24, speed);
        robot.moveLeftInches(28, speed);
        robot.turnRightDegrees(185, speed);
        robot.highSlideBucket();
        robot.moveBackwardInches(6, speed);
        robot.waitForSlideMotors();
        robot.moveBackwardInches(8, speed);
        //dont forget to close your code next time ;) (i would hate it if things were deleted
        // because they were closed wrong and didnt get saved)


    }

}
