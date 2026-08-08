package org.firstinspires.ftc.teamcode.level2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "L2 First Hardware", group = "Level 2")
public class FirstHardwareOpMode extends LinearOpMode {
    private DcMotor benchMotor;

    @Override
    public void runOpMode() {
        // Area 1: Get hardware from the robot configuration.
        DcMotor benchMotor = hardwareMap.get(DcMotor.class, "bench_motor");
        benchMotor.setPower(0.0);
        benchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Area 2: Wait for the driver to press START.
        waitForStart();

        // Area 3: Repeat while the OpMode is active.
        while (opModeIsActive()) {
            // Area 4: Read an input and use it to command hardware.
            double newPower = -gamepad1.left_stick_y * .25;
            benchMotor.setPower(newPower);
        }

        benchMotor.setPower(0.0);
    }
}