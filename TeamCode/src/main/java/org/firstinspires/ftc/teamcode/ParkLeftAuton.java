package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class ParkLeftAuton extends LinearOpMode {

    public Goldfish robot;



    public ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() {

        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);

        double speed = 0.7;
        long rest = 100;

        waitForStart();

        //robot parks near the sample prison
        robot.moveLeftInches(6, speed);
        robot.moveForwardInches(48, speed);
        robot.moveRightInches(8, speed);


    }
}
