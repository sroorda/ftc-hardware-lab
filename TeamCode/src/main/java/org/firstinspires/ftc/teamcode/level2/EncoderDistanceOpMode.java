package org.firstinspires.ftc.teamcode.level2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "L2 Encoder Distance", group = "Level 2")
public class EncoderDistanceOpMode extends LinearOpMode {
    private DcMotor benchMotor;
    private static final double TICKS_PER_MOTOR_REV = 537.7;
    private static final double TEST_POWER = 0.25;

    private static final double DRIVE_GEAR_REDUCTION = 1.0;
    private static final double WHEEL_DIAMETER_INCHES = 4.0;
    private static final double MOVE_DISTANCE_INCHES = 6.0;

    private static final double TICKS_PER_WHEEL_REV = TICKS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION;
    private static final double INCHES_PER_WHEEL_REV = WHEEL_DIAMETER_INCHES * Math.PI;
    private static final double TICKS_PER_INCH = TICKS_PER_WHEEL_REV / INCHES_PER_WHEEL_REV;

    @Override
    public void runOpMode() {
        // Area 1: Map and configure the motor.
        benchMotor = hardwareMap.get(DcMotor.class, "bench_motor");
        benchMotor.setPower(0.0);
        benchMotor.setDirection(DcMotor.Direction.FORWARD);
        benchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        benchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        benchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int targetTicks = (int) Math.round(MOVE_DISTANCE_INCHES * TICKS_PER_INCH);

        telemetry.addData("Requested distance", "%.2f in", MOVE_DISTANCE_INCHES);
        telemetry.addData("Inches in 1 revolution", "%.2f", INCHES_PER_WHEEL_REV);
        telemetry.addData("Ticks per inch", "%.2f", TICKS_PER_INCH);
        telemetry.addData("Target ticks", targetTicks);
        telemetry.addData("Current ticks", benchMotor.getCurrentPosition());
        telemetry.update();

        // Wait for PLAY.
        waitForStart();

        // Area 2: Command one encoder movement.
        if (opModeIsActive()) {
            benchMotor.setTargetPosition(targetTicks);
            benchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            benchMotor.setPower(TEST_POWER);

            while (opModeIsActive() && benchMotor.isBusy()) {
                telemetry.addData("Target ticks", targetTicks);
                telemetry.addData("Current ticks", benchMotor.getCurrentPosition());
                telemetry.update();
                idle();
            }
        }

        // Area 3: Leave the motor stopped.
        benchMotor.setPower(0.0);
        benchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}