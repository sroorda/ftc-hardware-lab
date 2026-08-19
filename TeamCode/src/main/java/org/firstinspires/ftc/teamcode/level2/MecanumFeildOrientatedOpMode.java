package org.firstinspires.ftc.teamcode.level2;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "L2 First Hardware", group = "Level 2")
public class MecanumFeildOrientatedOpMode extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    double forward, strafe, rotate;

    public void init(){
        drive.init(hardwareMap);
    }

    boolean xPreviousPressed = false;
    boolean autoToggledOn = false;

    public void loop(){
        double leftStickX = gamepad1.left_stick_x;
        double leftStickY = -gamepad1.left_stick_y;
        double rightStickX = gamepad1.right_stick_x;
        boolean xButton = gamepad1.x;

        if (xButton && !xPreviousPressed) {
            autoToggledOn = !autoToggledOn;
        }

        xPreviousPressed = xButton;

        telemetry.addData("x", xButton);
        telemetry.addData("auto", autoToggledOn);
        telemetry.update();

        if(autoToggledOn){
            if(leftStickY == 0){
                leftStickY = 0.3;
            } else{
                autoToggledOn = false;
            }
        }
        drive.driveFeildRelitive(leftStickY, leftStickX, rightStickX);
    }
}
