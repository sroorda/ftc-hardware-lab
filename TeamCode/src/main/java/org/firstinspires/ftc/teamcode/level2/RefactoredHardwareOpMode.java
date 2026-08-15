package org.firstinspires.ftc.teamcode.level2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.teamcode.level2.hardware.TestBenchHardware;

@TeleOp(name = "L2 Refactored Hardware", group = "Level 2")
public class RefactoredHardwareOpMode extends LinearOpMode {
    private static final String LOG_TAG = "L2Combined";
    private static final double POSITION_STEP = 0.05;
    private static final double RUN_POWER = 0.25;

    private final TestBenchHardware bench = new TestBenchHardware();

    @Override
    public void runOpMode() {
        bench.initialize(hardwareMap);
        double targetPosition = bench.getPositionServoPosition();

        boolean continuousServoRunning = false;

        telemetry.addData("Status", "Initialized");
        telemetry.addData("Motor power", "%.2f", bench.getMotorPower());
        telemetry.addData("Position target", "%.2f", targetPosition);
        telemetry.addData("CR-servo power", "%.2f", bench.getContinuousServoPower());
        telemetry.update();
        RobotLog.ii(LOG_TAG, "OpMode initialized");

        waitForStart();

        if (opModeIsActive()) {
            RobotLog.ii(LOG_TAG, "OpMode started");
        }

        while (opModeIsActive()) {
            double rawStickY = gamepad1.left_stick_y;
            double requestedMotorPower = -rawStickY;
            bench.setMotorPower(requestedMotorPower);

            if (gamepad1.dpadUpWasPressed()) {
                targetPosition += POSITION_STEP;
            } else if (gamepad1.dpadDownWasPressed()) {
                targetPosition -= POSITION_STEP;
            }

            targetPosition = Range.clip(targetPosition, 0.0, 1.0);
            bench.setPositionServoPosition(targetPosition);

            if (gamepad1.crossWasPressed()) {
                continuousServoRunning = !continuousServoRunning;
            }

            double requestedServoPower = continuousServoRunning ? RUN_POWER : 0.0;
            bench.setContinuousServoPower(requestedServoPower);

            telemetry.addData("Status", "Running");
            telemetry.addData("Raw stick Y", "%.2f", rawStickY);
            telemetry.addData("Requested motor power", "%.2f", requestedMotorPower);
            telemetry.addData("Applied motor power", "%.2f", bench.getMotorPower());
            telemetry.addData("Position target", "%.2f", targetPosition);
            telemetry.addData("Commanded position", "%.2f", bench.getPositionServoPosition());
            telemetry.addData("CR servo running", continuousServoRunning);
            telemetry.addData("CR-servo power", "%.2f", bench.getContinuousServoPower());
            telemetry.update();
        }

        bench.stopAll();
        RobotLog.ii(LOG_TAG, "OpMode stopped");
    }
}