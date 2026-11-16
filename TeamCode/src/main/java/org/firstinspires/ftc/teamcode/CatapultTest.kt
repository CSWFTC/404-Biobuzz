package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import org.firstinspires.ftc.teamcode.helpers.Catapult
import org.firstinspires.ftc.teamcode.helpers.GamepadButton

@TeleOp(name = "Catapult Test")
class CatapultTest : LinearOpMode() {
    override fun runOpMode() {
        val catapult = Catapult(hardwareMap)

        val resetButton = GamepadButton()
        val fireButton = GamepadButton()

        waitForStart()
        runBlocking {
            while (opModeIsActive()) {
                if (resetButton.justPressed(gamepad2.x))
                    launch {
                        catapult.reset()
                    }
                if (fireButton.justPressed(gamepad2.y))
                    launch {
                        catapult.fire()
                    }

                yield()
            }
        }
    }
}