package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class ParkRightAuton extends LinearOpMode {

    public Goldfish robot;

    public ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() {

        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);

        waitForStart();

        robot.moveRightInches(12, .5);
        robot.moveBackwardInches(18, 0.5);
        robot.rest(100);
        robot.turnRight(.5);
        robot.rest(100);
        robot.turnLeftDegrees(180, 0.75);
        robot.rest(100);
        robot.moveArmTicks(700, .5);
        robot.rest(100);
        robot.clawOpenTicks(.5);
        robot.rest(100);
        robot.moveArmTicks(-700, .5);
        robot.rest(100);
        robot.clawCloseTicks(.5);
        robot.rest(100);
        robot.moveRightInches(48, .5);
        robot.rest(100);
        robot.moveBackwardInches(18, 0.5);

    }

}
