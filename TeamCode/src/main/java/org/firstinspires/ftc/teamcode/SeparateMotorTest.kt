package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.helpers.Drivetrain

@TeleOp(name = "Separate Motor Test")
class SeparateMotorTest : LinearOpMode() {
    override fun runOpMode() {
        val dt = Drivetrain(hardwareMap)
        waitForStart()
        while (opModeIsActive()) {
            dt.fr.power = if (gamepad1.b) 1.0 else 0.0
            dt.fl.power = if (gamepad1.y) 1.0 else 0.0
            dt.rr.power = if (gamepad1.a) 1.0 else 0.0
            dt.rl.power = if (gamepad1.x) 1.0 else 0.0
        }
    }
}
