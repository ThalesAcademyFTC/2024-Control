package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class AUTONOMOOSE extends LinearOpMode {

    private Goldfish robot;
    private ElapsedTime runtime = new ElapsedTime();


    public void runOpMode() {

        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);

        waitForStart();

      /*  robot.moveForwardInches(50, .25);
        robot.rest(100);
        robot.moveRightInches(50, .25);
        robot.rest(100);
        robot.moveBackwardInches(50, .25);
        robot.rest(100);
        robot.moveLeftInches(50, .25);
    */    robot.rest(100);
        robot.turnRightDegrees(1800, .25);
        robot.rest(100);
        robot.turnLeftDegrees(1800, .25);


    }

}
