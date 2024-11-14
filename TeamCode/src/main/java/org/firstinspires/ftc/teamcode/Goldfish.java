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
import com.qualcomm.robotcore.hardware.ColorSensor;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import java.util.List;

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

    private VisionPortal visionPortal;
    private AprilTagProcessor aprilTag;

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


    public ColorSensor colorSensor;

/*  the public int methods below are for the color sensor to be used in the teleop and auton
    The return allows it to be used in the if statements in teleop.
    This would be formatted as: (For exmaple) 
    if (robot.getRed() > 100 && robot.getGreen() > 100) {
    robot.openClaw();
    }           
*/

    public int inch = 50; //ticks per inch

    public int getRed() {
        return colorSensor.red();
    }

    public int getGreen() {
        return colorSensor.green();
    }

    public int getBlue() {
        return colorSensor.blue();
    }
//brightness of the color
    public int getAlpha() {
        return colorSensor.alpha();
    }

    public boolean isColor(String color) {
        int red = getRed();
        int green = getGreen();
        int blue = getBlue();

        // EXAMPLE:
//      if (robot.isColor("green")) {
//      robot.moveForward(100, 0.5);
//      }
            

    //Detect if a specific color is predominant using isColor("red") (or "green"/"blue")
        switch(color.toLowerCase()) {
            case "red":
                return red > green && red > blue;
            case "green":
                return green > red && green > blue;
            case "blue":
                return blue > red && blue > green;
            default:
                return false;
        }
    }

    public void setupHardware() {
        switch (drive) {
            case MECHANUM:
                // Initialize color sensor
                colorSensor = hwMap.colorSensor.get("colorSensor");
                
                // Initialize motors
                motorFL = hwMap.dcMotor.get("motorFL");
                motorFR = hwMap.dcMotor.get("motorFR");
                motorBL = hwMap.dcMotor.get("motorBL");
                motorBR = hwMap.dcMotor.get("motorBR");

                motorFL.setDirection(DcMotorSimple.Direction.REVERSE);
                // motorFR.setDirection(DcMotorSimple.Direction.REVERSE);
                motorBL.setDirection(DcMotorSimple.Direction.REVERSE);
                // motorBR.setDirection(DcMotorSimple.Direction.REVERSE);

                // Initialize the webcam using the configured name in the Robot Controller
                webcamName = hwMap.get(WebcamName.class, "Webcam 1");

                armMotor = hwMap.dcMotor.get("armMotor");
                // suspensionMotor = hwMap.dcMotor.get("suspensionMotor");
                // clawServo = hwMap.servo.get("clawServo");

                allMotors = new DcMotor[] {motorFL, motorFR, motorBL, motorBR};

                // Create and configure the AprilTag processor
                aprilTag = new AprilTagProcessor.Builder()
                    .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)           // Set which AprilTag family to look for (36h11 is standard)
                    .setDrawTagID(true)
                    .build();
                
                // Create and start the VisionPortal which connects camera to processor
                visionPortal = new VisionPortal.Builder()
                    .setCamera(webcamName)              // Tell it which camera to use
                    .addProcessor(aprilTag)             // Add our AprilTag processor to the portal
                    .build();

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

    /**
     * Returns a list of all AprilTags currently visible to the camera
     * @return List of AprilTagDetection objects, each containing data about a visible tag
     */
    public List<AprilTagDetection> getAprilTags() {
        return aprilTag.getDetections();  // Get all currently detected tags
    }
    
    /**
     * Searches for and returns a specific AprilTag by its ID number
     * @param id The ID number of the AprilTag to find
     * @return AprilTagDetection object if found, null if not found
     */
    public AprilTagDetection getSpecificTag(int id) {
        // Get list of all visible tags
        List<AprilTagDetection> detections = getAprilTags();
        // Look through each detected tag
        for (AprilTagDetection detection : detections) {
            // If we find the tag ID we're looking for, return it
            if (detection.id == id) {
                return detection;
            }
        }
        // If we didn't find the tag, return null
        return null;
    }
    
    /**
     * Properly closes the camera and vision processing system
     * Should be called when OpMode ends
     */
    public void stopCamera() {
        if (visionPortal != null) {
            visionPortal.close();  // Safely shuts down the camera
        }
    }

    }
//fart




 

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        // love billy to death, but he taught us none of this. the stinky beta poopoo head
