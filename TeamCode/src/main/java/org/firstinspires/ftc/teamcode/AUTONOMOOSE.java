package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class AUTONOMOOSE extends LinearOpMode {

    private Goldfish robot;
    private ElapsedTime runtime = new ElapsedTime();


    public void runOpMode() {

        Goldfish = new robot(this, Goldfish.Drivetrain.MECHANUM);

    }

}
