package org.firstinspires.ftc.teamcode.level2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name = "L2 Color Sensor", group = "Level 2")
public class ColorSensorOpMode extends LinearOpMode {
    private ColorSensor colorSensor;

    @Override
    public void runOpMode() {
        // Area 1: Map the sensor.
        colorSensor = hardwareMap.get(ColorSensor.class, "color_sensor");

        telemetry.addData("Status", "Color sensor initialized");
        telemetry.update();

        // Area 2: Wait for PLAY.
        waitForStart();

        // Area 3: Read, interpret, and report the sensor.
        while (opModeIsActive()) {
            int red = colorSensor.red();
            int green = colorSensor.green();
            int blue = colorSensor.blue();
            int alpha = colorSensor.alpha();

            String detectedColor = "UNKNOWN";

            if (red > green && red > blue) {
                detectedColor = "RED";
            } else if (green > red && green > blue) {
                detectedColor = "GREEN";
            } else if (blue > red && blue > green) {
                detectedColor = "BLUE";
            }

            telemetry.addData("Red", red);
            telemetry.addData("Green", green);
            telemetry.addData("Blue", blue);
            telemetry.addData("Alpha", alpha);
            telemetry.addData("Detected color", detectedColor);
            telemetry.update();
        }
    }
}