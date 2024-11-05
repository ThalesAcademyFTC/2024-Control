package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ParkRightAuton extends LinearOpMode {

    public Goldfish robot;

    public ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() {

        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);

        waitForStart();

        robot.moveRightInches(48, .5);
        robot.rest(100);

    }

}
