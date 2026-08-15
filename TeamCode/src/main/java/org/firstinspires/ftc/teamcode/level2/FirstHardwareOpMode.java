package org.firstinspires.ftc.teamcode.level2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.RobotLog;

@TeleOp(name = "L2 First Hardware", group = "Level 2")
public class FirstHardwareOpMode extends LinearOpMode {
    private DcMotor benchMotor;
    private static final String LOG_TAG = "L2Hardware";

    @Override
    public void runOpMode() {
        benchMotor = hardwareMap.get(DcMotor.class, "bench_motor");
        benchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        benchMotor.setPower(0.0);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        RobotLog.ii(LOG_TAG, "OpMode initialized");

        waitForStart();

        if (opModeIsActive()) {
            RobotLog.ii(LOG_TAG, "OpMode started");
        }

        while (opModeIsActive()) {
            double rawStickY = gamepad1.left_stick_y;
            double requestedPower = -rawStickY;
            benchMotor.setPower(requestedPower);

            telemetry.addData("Status", "Running");
            telemetry.addData("Raw Stick Y", "%.2f", rawStickY);
            telemetry.addData("Requested Power", "%.2f", requestedPower);
            telemetry.addData("Applied Power", "%.2f", benchMotor.getPower());
            telemetry.update();

        }

        benchMotor.setPower(0.0);
        RobotLog.ii(LOG_TAG, "Opmode Stopped");
    }
}