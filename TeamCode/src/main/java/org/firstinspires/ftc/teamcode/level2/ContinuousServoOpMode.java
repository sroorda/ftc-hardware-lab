package org.firstinspires.ftc.teamcode.level2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "L2 Continuous Servo", group = "Level 2")
public class ContinuousServoOpMode extends LinearOpMode {
    private static final double RUN_POWER = 0.25;
    private static final double STOP_POWER = 0.0;

    private CRServo continuousServo;

    @Override
    public void runOpMode() {
        // Area 1: Map and initialize the servo.
        continuousServo = hardwareMap.get(CRServo.class, "continuous_servo");

        boolean servoRunning = false;
        continuousServo.setPower(STOP_POWER);

        telemetry.addData("Status", "Servo stopped");
        telemetry.addData("Power command", "%.2f", continuousServo.getPower());
        telemetry.update();

        // Area 2: Wait for PLAY.
        waitForStart();

        // Area 3: Read the gamepad and command the servo.
        while (opModeIsActive()) {
            if (gamepad1.aWasPressed()) {
                servoRunning = !servoRunning;
            }
            double requestedPower = servoRunning ? RUN_POWER : STOP_POWER;
            continuousServo.setPower(requestedPower);

            telemetry.addData("Running", servoRunning);
            telemetry.addData("Requested power command", "%.2f", requestedPower);
            telemetry.addData("Stored power command","%.2f", continuousServo.getPower());
            telemetry.update();
        }

        // Area 4: Stop the servo when the OpMode ends.
        continuousServo.setPower(STOP_POWER);
    }
}