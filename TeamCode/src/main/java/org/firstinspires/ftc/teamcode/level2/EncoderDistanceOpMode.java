package org.firstinspires.ftc.teamcode.level2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "L2 Encoder Distance", group = "Level 2")
public class EncoderDistanceOpMode extends LinearOpMode {
    private static final double COUNTS_PER_MOTOR_REV = 537.7;
    private static final double TEST_POWER = 0.25;
    private static final double TIMEOUT_SECONDS = 5.0;

    private final ElapsedTime runtime = new ElapsedTime();
    private DcMotor benchMotor;

    @Override
    public void runOpMode() {
        // Initialization: this runs after INIT and before PLAY.
        benchMotor = hardwareMap.get(DcMotor.class, "bench_motor");
        benchMotor.setPower(0.0);
        benchMotor.setDirection(DcMotor.Direction.FORWARD);
        benchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        benchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        benchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double numberOfRevolutions = 1.0;
        int targetTicks = (int) Math.round(numberOfRevolutions * COUNTS_PER_MOTOR_REV);
        telemetry.addData("Requested revolutions", "%.1f", numberOfRevolutions);
        telemetry.addData("Target ticks", targetTicks);
        telemetry.addData("Current ticks", benchMotor.getCurrentPosition());
        telemetry.update();

        // Pause here until the driver presses PLAY.
        waitForStart();

        if (opModeIsActive()) {
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

            benchMotor.setPower(0.0);
            benchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        // Leave the motor stopped when the OpMode finishes.
        benchMotor.setPower(0.0);
    }
}