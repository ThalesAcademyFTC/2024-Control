package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class AUTONOMOOSE extends LinearOpMode {

    public Goldfish robot;
    public ElapsedTime runtime = new ElapsedTime();

    double rest = 75;


    public void runOpMode() {

        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);


        waitForStart();

        robot.armCollect();
        robot.waitForArmMotor();
        robot.clawServo.setPosition(.6);
        robot.rest(rest);
        robot.armDump();
        robot.waitForArmMotor();
        robot.armReset();
        robot.basketRest();
        robot.waitForArmMotor();




    }

}
