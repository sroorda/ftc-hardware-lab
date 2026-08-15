package org.firstinspires.ftc.teamcode.level2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "L2 Encoder Distance", group = "Level 2")
public class EncoderDistanceOpMode extends LinearOpMode {
    private DcMotor benchMotor;
    private static final double TICKS_PER_MOTOR_REV = 0.0;
    private static final double TEST_POWER = 0.25;

    @Override
    public void runOpMode() {
        // Area 1: Map and configure the motor.
        benchMotor = hardwareMap.get(DcMotor.class, "bench_motor");
        benchMotor.setPower(0.0);
        benchMotor.setDirection(DcMotor.Direction.FORWARD);
        benchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        benchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        benchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int targetTicks = (int) Math.round(TICKS_PER_MOTOR_REV);

        telemetry.addData("Status", "Encoder reset");
        telemetry.addData("Movement", "One revolution");
        telemetry.addData("Target ticks", targetTicks);
        telemetry.addData("Current ticks", benchMotor.getCurrentPosition());
        telemetry.update();

        // Wait for PLAY.
        waitForStart();
        if (!opModeIsActive()) {
            benchMotor.setPower(0.0);
            return;
        }

        // Area 2: Command one encoder movement.
        benchMotor.setTargetPosition(targetTicks);
        benchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        benchMotor.setPower(TEST_POWER);

        // Area 3: Monitor movement and keep final telemetry visible.
        while( opModeIsActive() ) {
            if(benchMotor.isBusy() || benchMotor.getCurrentPosition() != targetTicks ) {
                telemetry.addData("Status", "Motor moving");
                telemetry.addData("Target ticks", targetTicks);
                telemetry.addData("Current ticks", benchMotor.getCurrentPosition());
            }
            else {
                benchMotor.setPower(0.0);

                telemetry.addData("Status", "Movement complete");
                telemetry.addData("Target ticks", targetTicks);
                telemetry.addData("Final ticks", benchMotor.getCurrentPosition());
            }

            telemetry.update();
        }

        // Area 4: Leave the motor stopped after Driver Station Stop.
        benchMotor.setPower(0.0);
        benchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}