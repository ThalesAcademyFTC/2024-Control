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

        robot.moveDiagonalNE(5, .25);
        robot.rest(100);
        robot.moveDiagonalSE(5, .25);
        robot.rest(100);
        robot.moveDiagonalSW(5, .25);
        robot.rest(100);
        robot.moveDiagonalNW(5, .25);
        robot.rest(100);



    }

}
