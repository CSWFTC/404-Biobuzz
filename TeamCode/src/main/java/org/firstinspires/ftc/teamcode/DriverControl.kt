package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.helpers.Drivetrain
import org.firstinspires.ftc.teamcode.helpers.GamepadButton
import org.firstinspires.ftc.teamcode.helpers.Intake
import org.firstinspires.ftc.teamcode.helpers.Catapult
import kotlin.math.abs
import kotlin.math.max
import kotlinx.coroutines.*

@TeleOp(name = "Driver Control")
class DriverControl : LinearOpMode() {
    override fun runOpMode() {
        val dt = Drivetrain(hardwareMap)
        val intake = Intake(hardwareMap)
        val catapult = Catapult(hardwareMap)

        val intakeButton = GamepadButton()
        val resetButton = GamepadButton()
        val fireButton = GamepadButton()

        waitForStart()
        runBlocking {
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
                if (resetButton.justPressed(gamepad2.x))
                    catapult.reset()
                if (fireButton.justPressed(gamepad2.y))
                    launch{
                        catapult.fire()
                    }

                yield()
            }
        }
    }
}