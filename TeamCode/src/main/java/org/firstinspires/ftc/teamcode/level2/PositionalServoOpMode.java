package org.firstinspires.ftc.teamcode.level2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "L2 Positional Servo", group = "Level 2")
    public class PositionalServoOpMode extends LinearOpMode {

    private static final double ZERO_POSITION = 0.0;
    private static final double POSITION_STEP = 0.05;
    private Servo positionServo;

        @Override
        public void runOpMode() {
            positionServo = hardwareMap.get(Servo.class, "position_servo");

            double targetPosition = ZERO_POSITION;
            positionServo.setPosition(targetPosition);

            telemetry.addData("Status", "Servo initialized");
            telemetry.addData("Position", "%.2f", targetPosition);
            telemetry.update();

            // Area 2: Wait for PLAY.
            waitForStart();

            // Area 3: Read the gamepad and command the servo.
            while (opModeIsActive()) {
                if (gamepad1.dpadUpWasPressed()) {
                    targetPosition += POSITION_STEP;
                } else if (gamepad1.dpadDownWasPressed()) {
                    targetPosition -= POSITION_STEP;
                }
                targetPosition = Range.clip(targetPosition, 0.0, 1.0);

                positionServo.setPosition(targetPosition);

                telemetry.addData("Target position", "%.2f", targetPosition);
                telemetry.addData("Commanded position", "%.2f", positionServo.getPosition());
                telemetry.update();

            }
        }
    }
