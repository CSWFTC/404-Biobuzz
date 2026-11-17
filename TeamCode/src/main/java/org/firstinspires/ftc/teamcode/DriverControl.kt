package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import org.firstinspires.ftc.teamcode.helpers.Drivetrain
import org.firstinspires.ftc.teamcode.helpers.GamepadButton
import org.firstinspires.ftc.teamcode.helpers.Intake
import org.firstinspires.ftc.teamcode.helpers.Outtake
import kotlin.math.abs
import kotlin.math.max


@TeleOp(name = "Driver Control")
class DriverControl : LinearOpMode() {
    override fun runOpMode() {
        val dt = Drivetrain(hardwareMap)
        val intake = Intake(hardwareMap)
        val outtake = Outtake(hardwareMap)
        val intakeButton = GamepadButton()
        val fireButton = GamepadButton()

        waitForStart()
        runBlocking {
            launch {
                outtake.loop()
            }
            while (opModeIsActive()) {
                val x = gamepad1.left_stick_x
                val y = -gamepad1.left_stick_y
                val spin = gamepad1.right_stick_x
                val denominator = max(1f, abs(x) + abs(y) + abs(spin)).toDouble()
                dt.fr.power = (x + y - spin) / denominator
                dt.rl.power = (x + y + spin) / denominator
                dt.rr.power = (y - x - spin) / denominator
                dt.fl.power = (y - x + spin) / denominator
                if (intakeButton.justPressed(gamepad2.a))
                    intake.toggle()
                if (fireButton.justPressed(gamepad2.y))
                    outtake.toggle()

                yield()
            }
        }
    }
}