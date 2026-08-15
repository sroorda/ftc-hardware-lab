package org.firstinspires.ftc.teamcode.level2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.RobotLog;

@TeleOp(name = "L2 Combined Hardware", group = "Level 2")
public class CombinedHardwareOpMode extends LinearOpMode {
    private static final String LOG_TAG = "L2Combined";
    private static final double MAX_MOTOR_POWER = 0.25;
    private static final double ZERO_POSITION = 0.0;
    private static final double POSITION_STEP = 0.05;
    private static final double RUN_POWER = 0.25;
    private static final double STOP_POWER = 0.0;

    private DcMotor benchMotor;
    private Servo positionServo;
    private CRServo continuousServo;

    @Override
    public void runOpMode() {
        benchMotor = hardwareMap.get(DcMotor.class, "bench_motor");
        positionServo = hardwareMap.get(Servo.class, "position_servo");
        continuousServo = hardwareMap.get(CRServo.class, "continuous_servo");

        benchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        benchMotor.setPower(0.0);

        double targetPosition = ZERO_POSITION;
        positionServo.setPosition(targetPosition);

        boolean continuousServoRunning = false;
        continuousServo.setPower(STOP_POWER);

        telemetry.addData("Status", "Initialized");
        telemetry.addData("Motor power", "%.2f", benchMotor.getPower());
        telemetry.addData("Position target", "%.2f", targetPosition);
        telemetry.addData("CR-servo power", "%.2f", continuousServo.getPower());
        telemetry.update();
        RobotLog.ii(LOG_TAG, "OpMode initialized");

        waitForStart();

        if (opModeIsActive()) {
            RobotLog.ii(LOG_TAG, "OpMode started");
        }

        while (opModeIsActive()) {
            double rawStickY = gamepad1.left_stick_y;
            double requestedMotorPower = -rawStickY;
            double appliedMotorPower = Range.clip(
                requestedMotorPower,
                -MAX_MOTOR_POWER,
                MAX_MOTOR_POWER);
            benchMotor.setPower(appliedMotorPower);

            if (gamepad1.dpadUpWasPressed()) {
                targetPosition += POSITION_STEP;
            } else if (gamepad1.dpadDownWasPressed()) {
                targetPosition -= POSITION_STEP;
            }

            targetPosition = Range.clip(targetPosition, 0.0, 1.0);
            positionServo.setPosition(targetPosition);

            if (gamepad1.crossWasPressed()) {
                continuousServoRunning = !continuousServoRunning;
            }

            double requestedServoPower =
                continuousServoRunning ? RUN_POWER : STOP_POWER;
            continuousServo.setPower(requestedServoPower);

            telemetry.addData("Status", "Running");
            telemetry.addData("Raw stick Y", "%.2f", rawStickY);
            telemetry.addData("Requested motor power", "%.2f", requestedMotorPower);
            telemetry.addData("Applied motor power", "%.2f", benchMotor.getPower());
            telemetry.addData("Position target", "%.2f", targetPosition);
            telemetry.addData("Commanded position", "%.2f", positionServo.getPosition());
            telemetry.addData("CR servo running", continuousServoRunning);
            telemetry.addData("CR-servo power", "%.2f", continuousServo.getPower());
            telemetry.update();
        }

        benchMotor.setPower(0.0);
        continuousServo.setPower(STOP_POWER);
        RobotLog.ii(LOG_TAG, "OpMode stopped");
    }
}