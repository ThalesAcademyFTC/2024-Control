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

        double speed = 0.6;
        long rest = 1000;

        waitForStart();

        robot.moveForwardInches(12, speed);
        sleep(rest);
        robot.moveLeftInches(12, speed);
        sleep(rest);
        robot.turnLeftDegrees(270, speed);
        sleep(rest);
        robot.moveForwardInches(12, speed);
        sleep(rest);
        robot.turnLeftDegrees(89,speed);
        sleep(rest);
        robot.moveLeftInches(112, speed);
        sleep(rest);



    }

}
