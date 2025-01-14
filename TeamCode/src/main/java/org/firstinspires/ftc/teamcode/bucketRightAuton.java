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
        //BUCKET ON THE ROBOT SHOULD BE FACING THE PLAYING FIELD BUCKET

        //Moves to bucket
        robot.moveBackwardInches(48, speed);
        sleep(rest);
        robot.moveDiagonalSW(24, speed);
        sleep(rest);
        robot.turnLeftDegrees(45, speed);
        sleep(rest);

        //Robot prepares to dump a sample
        robot.highSlideBucket();
        robot.moveBackwardInches(8, speed);
        robot.waitForSlideMotors();
        sleep(rest);

        //Robot places a sample
        robot.basketDown();
        robot.basketUp();
        sleep(850);
        robot.moveForwardInches(1, speed);

        //Robot backs up and limbs reset
        robot.moveForwardInches(2,speed);
        robot.resetSlide();
        robot.basketRest();
        robot.waitForSlideMotors();
        sleep(rest);

        //Robot turns and moves to the middle sample
        robot.turnLeftDegrees(45,speed);
        robot.basketUp();
        robot.moveForwardInches(34,speed);

        //Robot picks up middle sample
        robot.openClaw();
        robot.armAwayBasket();
        robot.moveForwardInches(2,speed);
        robot.closeClaw();
        robot.armToBasket();
        sleep(rest);

        //Robot returns to the bucket while placing sample in the basket
        robot.turnRightDegrees(45, speed);
        robot.openClaw();
        robot.armAwayBasket();
        robot.basketRest();
        robot.armToBasket();
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
        sleep(850);
        robot.moveForwardInches(2, speed);

        //Robot parks
        //Robot can park in two places. COMMENT OUT whichever one you don't need.


        //robot parks in the corner
        robot.moveRightInches(118, speed);

        //robot parks near the sample prison
        robot.moveDiagonalNE(32, speed);
        robot.moveForwardInches(24, speed);
        robot.moveRightInches(8, speed);


    }

}
