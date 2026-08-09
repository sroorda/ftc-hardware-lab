package org.firstinspires.ftc.teamcode.level2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.RobotLog;

@TeleOp(name = "L2 First Hardware", group = "Level 2")
public class FirstHardwareOpMode extends LinearOpMode {
    private static final String LOG_TAG = "L2Hardware";

    private DcMotor benchMotor;

    @Override
    public void runOpMode() {
        // Area 1: Get hardware from the robot configuration.
        benchMotor = hardwareMap.get(DcMotor.class, "bench_motor");
        benchMotor.setPower(0.0);
        benchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // initialization logging and telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        RobotLog.ii(LOG_TAG, "OpMode initialized");

        // Area 2: Wait for the driver to press START.
        waitForStart();

        // if op mode started, log it
        if (opModeIsActive()) {
            RobotLog.ii(LOG_TAG, "OpMode started");
        }

        // Area 3: Repeat while the OpMode is active.
        while (opModeIsActive()) {
            double rawStickY = gamepad1.left_stick_y;
            double requestedPower = -rawStickY * 0.25;
            benchMotor.setPower(requestedPower);

            telemetry.addData("Status", "Running");
            telemetry.addData("Raw stick Y", "%.2f", rawStickY);
            telemetry.addData("Requested power", "%.2f", requestedPower);
            telemetry.addData("Applied power", "%.2f", benchMotor.getPower());
            telemetry.update();
        }

        benchMotor.setPower(0.0);
        RobotLog.ii(LOG_TAG, "OpMode stopped");
    }
}