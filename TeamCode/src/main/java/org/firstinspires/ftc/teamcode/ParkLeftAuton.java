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

        waitForStart();

        robot.moveRightInches(96, .5);
        robot.rest(100);



    }
}
