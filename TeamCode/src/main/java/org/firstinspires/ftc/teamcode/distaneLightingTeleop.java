package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.LED;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "distanceLightingTeleop")
public class distaneLightingTeleop extends OpMode {

    Goldfish robot;

    // distance sensor pulls from type Rev2mDistanceSensor
    Rev2mDistanceSensor distanceSensor;

    // redLED and greenLED pulls from type LED
    LED redLED;
    LED greenLED;

    @Override
    public void init() {

        // makes a function to recieve the distance named "distanceSensor"
        distanceSensor = hardwareMap.get(Rev2mDistanceSensor.class, "distanceSensor");

        // makes a function to turn red LEDs on/off named "redLED"
        redLED = hardwareMap.get(LED.class, "redLED");

        // makes a function to turn green LEDs on/off named "greenLED"
        greenLED = hardwareMap.get(LED.class, "greenLED");

    }
    @Override
    public void loop() {

        // distance is distance...
        double distance = distanceSensor.getDistance(DistanceUnit.CM);


        // adds telem data for distance // distance replaces %.3f so that it shows "distance ___ cm"
        telemetry.addData("distance", "%.3f cm", distance);

        // if distance is less than 5cm LED glows red
        if (distance <= 30 && distance > 15) { redLED.enable(false); greenLED.enable(true); }

        // if distance is less than 10 cm LED glows yellow
        else if (distance <= 15 && distance > 5) { redLED.enable(true); greenLED.enable(true); }

        // if distance is less than 15 cm LED glows green
        else if (distance <= 5) { redLED.enable(true); greenLED.enable(false); }

        // if distance is above 15 then LEDs turn off
        else { redLED.enable(false); greenLED.enable(false); }


    }
}