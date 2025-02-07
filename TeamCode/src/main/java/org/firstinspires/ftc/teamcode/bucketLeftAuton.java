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
        long rest = 50;

        robot.basketDown();
        robot.armDump();

        waitForStart();

        //IMPORTANT!!!
        //BUCKET ON THE ROBOT SHOULD BE FACING THE PLAYING FIELD BUCKET



        //Arm and basket extend out
        robot.armReset();
        robot.basketRest();

        //Moves to bucket
        robot.moveDiagonalSW(30, speed);
            sleep(rest);
        robot.turnLeftDegrees(45, speed);
            sleep(rest);

        //Robot prepares to dump a sample
        robot.highSlideBucket();
        robot.waitForSlideMotors();
        robot.moveBackwardInches(9, speed/1.5);


        //Robot dumps a sample
        robot.basketUp();

        //Robot backs up and limbs reset
        robot.moveForwardInches(3,speed);
        robot.resetSlide();
        robot.basketRest();
        sleep(rest);

        //SECOND CYCLE
        //Robot turns and moves to the middle sample
        robot.turnLeftDegrees(45,speed);
        // Work for a scooby snack <3
        sleep(rest);
        robot.armCollect();
        robot.moveForwardInches(6,speed*1.5);
        robot.waitForArmMotor();

        //Robot picks up middle sample
        robot.moveRightInches(7, speed);
        robot.closeClaw();
        sleep(350);

        //Robot returns to the bucket while placing sample in the basket
        robot.armDump();
        robot.basketDown();
        robot.turnRightDegrees(45, speed);
        robot.openClaw();
        sleep(rest*2);
        robot.clawMoveServo.setPosition(0.6);
        sleep(500);
        robot.armReset();
        robot.basketRest();
        robot.moveBackwardInches(7, speed);

        //Robot prepares to dump a sample
        robot.highSlideBucket();
        robot.waitForSlideMotors();
        robot.moveBackwardInches(3, speed);

        //Robot places a sample
        robot.basketDown();
        sleep(rest*2);
        robot.basketUp();
        robot.moveForwardInches(3, speed);
        robot.resetSlide();
        robot.turnRightDegrees(45, speed);
        robot.waitForSlideMotors();
        //Robot parks
        //Robot can park in two places. COMMENT OUT whichever one you don't need.

/*
        //robot parks in the inspection zone
        robot.moveRightInches(118, speed);

        //robot parks near the sample prison
        /*robot.moveDiagonalNE(32, speed);
        robot.moveForwardInches(24, speed);
        robot.moveRightInches(8, speed);*/


    }

}
