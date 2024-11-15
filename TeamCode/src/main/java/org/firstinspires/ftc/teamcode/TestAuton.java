package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@Autonomous(name = "Test Auton")
public class TestAuton extends LinearOpMode {
    public Goldfish robot;
    public ElapsedTime runtime = new ElapsedTime();

    private VisionPortal visionPortal;
    private AprilTagProcessor aprilTag;

    public void runOpMode() {
        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);
        
        // Initialize April Tag detection
        aprilTag = new AprilTagProcessor.Builder().build();
        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .addProcessor(aprilTag)
                .build();

        // \/\/ Before the start button is pressed, continuously display color sensor values \/\/
        // \/\/ This helps with debugging and calibration \/\/
        while (!isStarted()) {
            telemetry.addData("Red", robot.getRed());     // Display red value
            telemetry.addData("Green", robot.getGreen()); // Display green value 
            telemetry.addData("Blue", robot.getBlue());   // Display blue value
            telemetry.addData("Status", "Initialized");   // Show robot is ready
            telemetry.addLine("\nApril Tags detected:");
            for (AprilTagDetection detection : aprilTag.getDetections()) {
                telemetry.addData("Tag ID", detection.id);
                telemetry.addData("Range (inches)", detection.ftcPose.range);
            }
            telemetry.update();                           // Push data to driver station
        }

        waitForStart(); // Wait for the start button to be pressed


        while (opModeIsActive()) { // Main robot loop - runs continuously until the stop button is pressed
            // \/\/ Gets current color sensor values \/\/
            int red = robot.getRed();
            int green = robot.getGreen(); 
            int blue = robot.getBlue();

            // \/\/ Display raw color values on driver station \/\/
            telemetry.addData("R:", red);
            telemetry.addData("G:", green);
            telemetry.addData("B:", blue);

            // Determine which color (if any) is dominant and display result    \/\/
            // Uses the isColor() method which compares the RGB values          \/\/
            if (robot.isColor("red")) {
                telemetry.addLine("Red color detected!");
            } else if (robot.isColor("green")) {
                telemetry.addLine("Green color detected!");
            } else if (robot.isColor("blue")) {
                telemetry.addLine("Blue color detected!");
            } else {
                telemetry.addLine("No dominant color detected");
            }
            
            telemetry.addLine("\nApril Tags detected:");
            for (AprilTagDetection detection : aprilTag.getDetections()) {
                telemetry.addData("Tag ID", detection.id);
                telemetry.addData("Range (inches)", detection.ftcPose.range);
            }
            
            telemetry.update(); // Sends the updated telemetry data to the driver station
        
        
        
        }
    }
}