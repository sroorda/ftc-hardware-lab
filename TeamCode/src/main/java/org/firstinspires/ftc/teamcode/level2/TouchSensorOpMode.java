package org.firstinspires.ftc.teamcode.level2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name = "L2 Touch Sensor", group = "Level 2")
public class TouchSensorOpMode extends LinearOpMode {
    private TouchSensor touchSensor;

    @Override
    public void runOpMode() {
        // Area 1: Map the sensor.
        touchSensor = hardwareMap.get(TouchSensor.class, "touch_sensor");

        telemetry.addData("Status", "Touch sensor initialized");
        telemetry.update();

        // Area 2: Wait for PLAY.
        waitForStart();

        // Area 3: Read and report the sensor.
        boolean previousPressed = false;
        boolean toggledOn = false;
        while (opModeIsActive()) {
            boolean pressed = touchSensor.isPressed();
            if (pressed && !previousPressed) {
                toggledOn = !toggledOn;
            }

            previousPressed = pressed;

            telemetry.addData("Pressed", pressed);
            telemetry.addData("Touch sensor", pressed ? "PRESSED" : "RELEASED");
            telemetry.addData("Toggled state", toggledOn ? "ON" : "OFF");

            telemetry.update();
        }
    }
}