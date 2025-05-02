package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "CodeLesson")
public class CodeLesson extends OpMode {

    Goldfish robot;


    @Override
    public void init() {


        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);

    }

    @Override

    public void loop() {






        telemetry.update();
    }
}