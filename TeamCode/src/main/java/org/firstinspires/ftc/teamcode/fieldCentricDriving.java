package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.LED;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "fieldLightingTeleop")
public class fieldCentricDriving extends OpMode {

    Goldfish robot;

    IMU imu;

    double rbtSpd = 1.2;

    @Override
    public void init() {

//      -=-=-=-=-=-=-=-=-=-=-=-=-// VARIABLES //-=-=-=-=-=-=-=-=-=-=-=-=-

        // sets x y variables for moving
        double y = -gamepad1.left_stick_y; // Y stick value is reversed
        double x = gamepad1.left_stick_x;
        // turning
        double rx = gamepad1.right_stick_x;

        // The value of where the robot is facing
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // motor power variables
        double motorFLPower = (rotY + rotX + rx) / rbtSpd;
        double motorBLPower = (rotY - rotX + rx) / rbtSpd;
        double motorFRPower = (rotY - rotX - rx) / rbtSpd;
        double motorBRPower = (rotY + rotX - rx) / rbtSpd;

//      -=-=-=-=-=-=-=-=-=-=-=-=-// CLASSES //-=-=-=-=-=-=-=-=-=-=-=-=-

        robot = new Goldfish(this, Goldfish.Drivetrain.MECHANUM);

        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

//      -=-=-=-=-=-=-=-=-=-=-=-=-// IMUs //-=-=-=-=-=-=-=-=-=-=-=-=-

        // Retrieve the IMU from the hardware map
        imu = hardwareMap.get(IMU.class, "imu");

        /* The IMU is essentially a compass inside the robot, but it needs to be orientated since
               ours are on the side, so this code sets the parameters */
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));

        // this confirms the parameters
        imu.initialize(parameters);

//      -=-=-=-=-=-=-=-=-=-=-=-=-// TELEMETRY //-=-=-=-=-=-=-=-=-=-=-=-=-

        telemetry.addData("controller Y: ", gamepad1.left_stick_y);

        telemetry.addData("FL:", motorFLPower);
        telemetry.addData("FR:", motorFRPower);
        telemetry.addData("BL:", motorBLPower);
        telemetry.addData("BR:", motorBRPower);

        telemetry.addData("controller X:", gamepad1.left_stick_x);
        telemetry.addData("robot heading:", botHeading);
        telemetry.addData("rotX:", rotX);
        telemetry.addData("rotY:", rotY);


    }

    @Override
    public void loop() {

//      -=-=-=-=-=-=-=-=-=-=-=-=-// TELEMETRY pt. 2 //-=-=-=-=-=-=-=-=-=-=-=-=-



//      -=-=-=-=-=-=-=-=-=-=-=-=-// VARIABLES //-=-=-=-=-=-=-=-=-=-=-=-=-

        // sets x y variables for moving
        double y = -gamepad1.left_stick_y; // Y stick value is reversed
        double x = gamepad1.left_stick_x;
        // turning
        double rx = gamepad1.right_stick_x;

        // The value of where the robot is facing
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // motor power variables
        double motorFLPower = (rotY + rotX + rx) / rbtSpd;
        double motorBLPower = (rotY - rotX + rx) / rbtSpd;
        double motorFRPower = (rotY - rotX - rx) / rbtSpd;
        double motorBRPower = (rotY + rotX - rx) / rbtSpd;


        // resets the yaw
        if (gamepad1.start) {
            imu.resetYaw();
        }

        // MOVEMENT !!! :D
        robot.motorFL.setPower(motorFLPower);
        robot.motorBL.setPower(motorBLPower);
        robot.motorFR.setPower(motorFRPower);
        robot.motorBL.setPower(motorBRPower);


    }
}