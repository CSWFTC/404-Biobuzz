package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.helpers.GamepadButton
import org.firstinspires.ftc.teamcode.helpers.Intake
import org.firstinspires.ftc.teamcode.helpers.SlipGearCatapult
import kotlin.math.abs
import kotlin.math.max

@TeleOp(name = "Driver Control")
class DriverControl : LinearOpMode() {
    override fun runOpMode() {
        val fr = hardwareMap.get("frontRight") as DcMotor
        val fl = hardwareMap.get("frontLeft") as DcMotor
        val rr = hardwareMap.get("rearRight") as DcMotor
        val rl = hardwareMap.get("rearLeft") as DcMotor
        val intake = Intake(hardwareMap)
        val catapult = SlipGearCatapult(hardwareMap)

        val intakeButton = GamepadButton()
        val prepareButton = GamepadButton()
        val fireButton = GamepadButton()

        waitForStart()
        while (opModeIsActive()) {
            val x = gamepad1.left_stick_x
            val y = gamepad1.left_stick_y
            val spin = gamepad1.right_stick_x
            val denominator = max(1f, abs(x) + abs(y) + abs(spin)).toDouble()
            fr.power = (x + y - spin) / denominator
            rl.power = (x + y + spin) / denominator
            rr.power = (y - x - spin) / denominator
            fl.power = (y - x + spin) / denominator

            if (intakeButton.justPressed(gamepad2.a))
                intake.toggle()
            if (prepareButton.justPressed(gamepad2.b))
                catapult.prepare()
            if (fireButton.justPressed(gamepad2.y))
                catapult.fire()
        }
    }
}