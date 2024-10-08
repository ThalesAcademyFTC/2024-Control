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

    public LinearOpMode auton;

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

    public Servo clawServo;

    public WebcamName webcamName;

    //constants here

    static final double TICKS_PER_INCH = 40;

    //Encoder ticks for ticks per inch
    //welovebilly

    public Goldfish(OpMode opMode, Drivetrain drivetrain) {

        this.hwMap = opMode.hardwareMap;

        this.drive = drivetrain;

        this.telem = opMode.telemetry;

        setupHardware();
    }

    public Goldfish(LinearOpMode opmode, Drivetrain type) {

        this.auton = opmode;

        hwMap = opmode.hardwareMap;

        telem = opmode.telemetry;

        drive = type;

        setupHardware();
    }

    RevBlinkinLedDriver lights;

    public Goldfish(HardwareMap hardwareMap, Drivetrain drivetrain) {

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

               // motorFL.setDirection(DcMotorSimple.Direction.REVERSE);
               // motorBL.setDirection(DcMotorSimple.Direction.REVERSE);
               // motorBR.setDirection(DcMotorSimple.Direction.REVERSE);

                webcamName = hwMap.get(WebcamName.class, "Webcam 1");

                armMotor = hwMap.dcMotor.get("armMotor");
                suspensionMotor = hwMap.dcMotor.get("suspensionMotor");
                clawServo = hwMap.servo.get("clawServo");

                break;

            default:

                telem.addLine("Invalid type" + drive + "passed to Spark's init function. Nothing has been set up");

                break;
        }
    }

    public void rest() {
        motorBL.setPower(0);
        motorFL.setPower(0);
        motorBR.setPower(0);
        motorFR.setPower(0);
    }

  public void move(double x, double y, double turn) {

        switch(drive) {

            case MECHANUM:

                double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), 1);

                double FLpower = (y + x + turn) / denominator;
                double BLpower = (y + x - turn) / denominator;
                double FRpower = (y - x - turn) / denominator;
                double BRpower = (y + x - turn) / denominator;

                motorFL.setPower(FLpower);
                motorBL.setPower(BLpower);
                motorFR.setPower(FRpower);
                motorBR.setPower(BRpower);

                break; //breakdance


        }
    }

   

    public void moveLeft(double speed) {
        move( -speed, 0, 0);
    }

    public void moveRight(double speed) {
        move( -speed, 0, 0);
    }

    public void moveForward(double speed) {
        move( 0, speed, 0);
    }

    public void moveBackward( double speed) {
        move( 0, -speed, 0);
    }

    public void turnLeft(double speed) {
        move(0, 0, -speed);
    }

    public void turnRight(double speed) {
        move(0, 0, speed);
    }

    public void liftArm() {
        armMotor.setPower(0.75);
    }

    public void lowerArm() {
        armMotor.setPower(-0.5);
    }

    public void setArmMotor(double power) {
        armMotor.setPower(power);
    }
    public void setMotorSuspend(double power) {
        suspensionMotor.setPower( power );
    }

    public void openClaw() {
        clawServo.setPosition(.6);
    }

    public void closeClaw() {
        clawServo.setPosition(.9);
    }


    }

 

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        // love billy to death, but he taught us none of this. the stinky beta poopoo head
