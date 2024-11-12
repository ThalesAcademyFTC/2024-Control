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

    public DcMotor[] allMotors;

    public Servo clawServo;

    public WebcamName webcamName;

    double inchtick = 50;

    double TICKS_PER_INCH = 50 ;


    //constants here


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

                motorFL.setDirection(DcMotorSimple.Direction.REVERSE);
               // motorFR.setDirection(DcMotorSimple.Direction.REVERSE);
                motorBL.setDirection(DcMotorSimple.Direction.REVERSE);
               // motorBR.setDirection(DcMotorSimple.Direction.REVERSE);

                //webcamName = hwMap.get(WebcamName.class, "Webcam 1");

                armMotor = hwMap.dcMotor.get("armMotor");
               //suspensionMotor = hwMap.dcMotor.get("suspensionMotor");
                //clawServo = hwMap.servo.get("clawServo");

                allMotors = new DcMotor[] {motorFL, motorFR, motorBL, motorBR};

                break;

            default:

                telem.addLine("Invalid type" + drive + "passed to Spark's init function. Nothing has been set up");

                break;
        }
    }

    public void rest(double time) {
        motorFL.setPower(0);
        motorFR.setPower(0);
        motorBL.setPower(0);
        motorBR.setPower(0);
    }

  public void move(double x, double y, double turn) {

        switch(drive) {

            case MECHANUM:

                double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), 1);

                double FLpower = (y + x + turn) / denominator;
                double BLpower = (y - x + turn) / denominator;
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
        move( speed, 0, 0);
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

    public void moveForwardInches( double inches, double speed) {
        int tickTarget = (int) Math.round(inches * inchtick);

        motorFL.setTargetPosition(tickTarget);
        motorBR.setTargetPosition(tickTarget);
        motorBL.setTargetPosition(tickTarget);
        motorFR.setTargetPosition(tickTarget);

        for (DcMotor x : allMotors) {

            x.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        }

        move(0, speed, 0);

        for (DcMotor x : allMotors) {

            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        waitForMotors();

        resetDriveEncoders();

    }

    public void moveBackwardInches(double inches, double speed) {

        moveForwardInches( -inches, -speed);

    }

    public void moveRightInches(double inches, double speed) {

        int tickTarget = (int)Math.round(inches * TICKS_PER_INCH);

        resetDriveEncoders();

        motorFL.setTargetPosition( tickTarget);
        motorFR.setTargetPosition( -tickTarget);
        motorBL.setTargetPosition( -tickTarget);
        motorBR.setTargetPosition( tickTarget);

        for(DcMotor x: allMotors) {
            x.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        move(speed, 0, 0);

        for (DcMotor x: allMotors) {
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        waitForMotors();

        resetDriveEncoders();

    }

    public void moveLeftInches(double inches, double speed) {

        moveRightInches(-inches, -speed);

    }

    public void moveDiagonalNE(double inches, double speed) {

        int tickTarget = (int)Math.round(inches * TICKS_PER_INCH);

        resetDriveEncoders();

        motorFL.setTargetPosition( tickTarget);
        motorFR.setTargetPosition(0);
        motorBL.setTargetPosition(0);
        motorBR.setTargetPosition( tickTarget);

        for(DcMotor x: allMotors) {
            x.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        move(speed, speed, 0);

        for (DcMotor x: allMotors) {
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        waitForMotors();

        resetDriveEncoders();

    }

    public void moveDiagonalSW(double inches, double speed) {

        moveDiagonalNE(-inches, -speed);

    }

    public void moveDiagonalNW(double inches, double speed) {
        int tickTarget = (int)Math.round(inches * TICKS_PER_INCH);

        resetDriveEncoders();

        motorFL.setTargetPosition(0);
        motorFR.setTargetPosition( tickTarget);
        motorBL.setTargetPosition( tickTarget);
        motorBR.setTargetPosition(0);

        for(DcMotor x: allMotors) {
            x.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        move(-speed, speed, 0);

        for (DcMotor x: allMotors) {
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        waitForMotors();

        resetDriveEncoders();

    }

    public void moveDiagonalSE(double inches, double speed) {

        moveDiagonalNW(-inches, -speed);

    }

    public void turnRightDegrees(int degrees, double speed) {

        int tickTarget = (int)Math.round(degrees * (1155/90));

        resetDriveEncoders();


        motorFL.setTargetPosition( tickTarget);
        motorFR.setTargetPosition( -tickTarget);
        motorBL.setTargetPosition( tickTarget);
        motorBR.setTargetPosition( -tickTarget);

        for(DcMotor x: allMotors) {
            x.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        move(0, 0, speed);

        for(DcMotor x: allMotors) {
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        waitForMotors();

        resetDriveEncoders();

    }

    public void turnLeftDegrees(int degrees, double speed) {

        turnRightDegrees(-degrees, -speed);

    }

        public void waitForMotors() {
            boolean finished = false;
            while (auton.opModeIsActive() && !finished && !auton.isStopRequested()) {
                if (motorFL.isBusy() || motorFR.isBusy() || motorBL.isBusy() || motorBR.isBusy()) {
                    telem.addData("front left encoder:", "%7d / % 7d", motorFL.getCurrentPosition(), motorFL.getTargetPosition());
                    telem.addData("back left encoder:", "%7d / % 7d", motorBL.getCurrentPosition(), motorBL.getTargetPosition());
                    telem.addData("front right encoder:", "%7d / % 7d", motorFR.getCurrentPosition(), motorFR.getTargetPosition());
                    telem.addData("back right encoder:", "%7d / % 7d", motorBR.getCurrentPosition(), motorBR.getTargetPosition());
                    telem.update();
                } else {
                    finished = true;
                }
             }
        }

    public void resetDriveEncoders() {
        for (DcMotor x : allMotors) {
            x.setPower(0);
            x.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            x.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        }


    }
//fart




 

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        // love billy to death, but he taught us none of this. the stinky beta poopoo head
