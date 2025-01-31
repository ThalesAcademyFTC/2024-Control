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
        robot.moveBackwardInches(9, speed);

        //Robot places a sample
        robot.basketDown();
        robot.basketUp();
        robot.waitForMotors();
            sleep(rest);

        //Robot backs up and limbs reset
        robot.moveForwardInches(3,speed);
        robot.resetSlide();
        robot.basketRest();
        sleep(rest);

        //SECOND CYCLE
        //Robot turns and moves to the middle sample
        robot.turnLeftDegrees(45,speed);
        robot.moveDiagonalNW(2, speed);
        robot.moveForwardInches(40,speed);

        //Robot picks up middle sample
        robot.armCollect();
        robot.openClaw();
        robot.waitForArmMotor();
        robot.moveRightInches(5, speed);
        robot.closeClaw();

        //Robot returns to the bucket while placing sample in the basket
        robot.armDump();
        robot.basketUp();
        robot.turnRightDegrees(45, speed);
        robot.openClaw();
        robot.armReset();
        robot.basketRest();
        robot.moveBackwardInches(38, speed);
        sleep(rest);

        //Robot prepares to dump a sample
        robot.highSlideBucket();
        robot.moveBackwardInches(2, speed);
        robot.waitForSlideMotors();
        sleep(rest);

        //Robot places a sample
        robot.basketDown();
        robot.basketUp();
        robot.moveForwardInches(2, speed);
        robot.resetSlide();
        sleep(rest);
        robot.turnRightDegrees(45, speed);

        //Robot parks
        //Robot can park in two places. COMMENT OUT whichever one you don't need.


        //robot parks in the inspection zone
        robot.moveRightInches(118, speed);

        //robot parks near the sample prison
        /*robot.moveDiagonalNE(32, speed);
        robot.moveForwardInches(24, speed);
        robot.moveRightInches(8, speed);*/


    }

}
