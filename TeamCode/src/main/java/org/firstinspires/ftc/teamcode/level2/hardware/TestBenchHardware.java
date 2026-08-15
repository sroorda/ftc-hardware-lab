package org.firstinspires.ftc.teamcode.level2.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public final class TestBenchHardware {
    private static final String MOTOR_NAME = "bench_motor";
    private static final double MAX_MOTOR_POWER = 0.25;
    private static final String POSITION_SERVO_NAME = "position_servo";
    private static final double ZERO_POSITION = 0.0;
    private static final String CONTINUOUS_SERVO_NAME = "continuous_servo";
    private static final double MAX_CONTINUOUS_SERVO_POWER = 0.25;

    private CRServo continuousServo;
    private Servo positionServo;
    private DcMotor benchMotor;

    public void initialize(HardwareMap hardwareMap) {
        benchMotor = hardwareMap.get(DcMotor.class, MOTOR_NAME);
        benchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        benchMotor.setPower(0.0);

        positionServo = hardwareMap.get(Servo.class, POSITION_SERVO_NAME);
        positionServo.setPosition(ZERO_POSITION);

        continuousServo = hardwareMap.get(CRServo.class, CONTINUOUS_SERVO_NAME);
        continuousServo.setPower(0.0);
    }

    // MOTOR
    public void setMotorPower(double requestedPower) {
        double appliedPower =
            Range.clip(requestedPower, -MAX_MOTOR_POWER, MAX_MOTOR_POWER);
        benchMotor.setPower(appliedPower);
    }

    public double getMotorPower() {
        return benchMotor.getPower();
    }

    public void stopAll() {
        benchMotor.setPower(0.0);
        continuousServo.setPower(0.0);
    }

    // POSITION SERVO
    public void setPositionServoPosition(double requestedPosition) {
        positionServo.setPosition(Range.clip(requestedPosition, 0.0, 1.0));
    }

    public double getPositionServoPosition() {
        return positionServo.getPosition();
    }

    // CONTINUOUS SERVO
    public void setContinuousServoPower(double requestedPower) {
        double appliedPower = Range.clip(
            requestedPower,
            -MAX_CONTINUOUS_SERVO_POWER,
            MAX_CONTINUOUS_SERVO_POWER);
        continuousServo.setPower(appliedPower);
    }

    public double getContinuousServoPower() {
        return continuousServo.getPower();
    }
}