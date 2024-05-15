package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public  class Goldfish {

    public HardwareMap hwMap;

    LinearOpMode auton;

    public enum Drivetrain {
        MECHANUM
    }

    public enum Team {
        RED,
        BLUE
    }

    public Drivetrain drive;

    public Telemetry telem;

    //defining global variables
    public DcMotor armMotor, suspensionMotor, motorFL, motorFR, motorBL, motorBR;

    public Servo clawServoLeft, clawServoRight;

    public WebcamName webcamName;

    //constants here

    static final double TICKS_PER_INCH = 40;

    //Encoder ticks for ticks per inch
    //welovebilly

    public Goldfish( OpMode opMode, Drivetrain drivetrain); {

        this.hwMap = opMode.hardwareMap;

        this.drive = Drivetrain;

        this.telem = opMode.telemetry;

        setupHardware();
    }

    public Goldfish( LinearOpMode opmode, Drivetrain type) {

        this.auton = opmode;

        hwMap = opmode.hardwareMap;

        telem = opmode.telemetry;

        drive = type;

        setupHardware();
    }

    RevBlinkinLedDriver lights;

    public Goldfish( HardwareMap hardwareMap, Drivetrain drivetrain) {

        this.hwMap = hardwareMap;

        this.drive = drivetrain;

        setupHardware();

    }


    public void setupHardware() {

        switch (drive) {

            case MECHANUM:

                motorFL = hwMap.dcMotor.get("motorFL");
                motorFR = hwMap.dcMotor.get("motorFR");
                motorBL = hwMap.dcMotor.get("motorBL");
                motorBR = hwMap.dcMotor.get("motorBR");

                motorFL.setDirection(DcMotorSimple.Direction.REVERSE);

        }

    }


}