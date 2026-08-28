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
        // TODO: retrieve bench_motor from hardwareMap
        // TODO: set a deliberate zero-power behavior and zero power
        benchMotor = hardwareMap.get(DcMotor.class, "bench_motor");
        benchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        benchMotor.setPower(0.0);

        // Area 2: Wait for the driver to press START.
        waitForStart();

        // Area 3: Repeat while the OpMode is active.
        while (opModeIsActive()) {
            // Area 4: Read an input and use it to command hardware.
            // TODO: calculate limited power from the left stick
            double requestedPower = -gamepad1.left_stick_y;
            double limitedPower = requestedPower * 0.25;
            // TODO: send that power to the motor
            benchMotor.setPower(limitedPower);

        }

        // TODO: return the motor to zero power
        benchMotor.setPower(0.0);
    }
}