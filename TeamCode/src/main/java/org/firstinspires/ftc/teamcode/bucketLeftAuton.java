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

        //IMPORTANT!!!
        //BUCKET ON THE ROBOT SHOULD BE FACING THE PLAYING FIELD BUCKET

        //Moves to bucket
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
            sleep(rest);

        //Robot limbs reset and robot turns
        robot.resetSlide();
        robot.basketRest();
        robot.turnLeftDegrees(45,speed);
        robot.waitForSlideMotors();
            sleep(rest);

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
