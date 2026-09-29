package org.firstinspires.ftc.teamcode.opmodes

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import kotlin.math.abs
import kotlin.math.max

class DriverControl: LinearOpMode() {
    override fun runOpMode() {
        val fr: DcMotor = hardwareMap.get("frontRight") as DcMotor
        val fl : DcMotor = hardwareMap.get("frontLeft") as DcMotor
        val rr: DcMotor  = hardwareMap.get("rearRight") as DcMotor
        val rl: DcMotor  = hardwareMap.get("rearLeft") as DcMotor
        waitForStart();
        while(opModeIsActive()){
            val x = gamepad1.left_stick_x
            val y = gamepad1.left_stick_y
            val spin = gamepad1.right_stick_x
            val denominator = max(1f, abs(x)  + abs(y) + abs(spin)).toDouble()
            fr.power = (x + y - spin) / denominator
            rl.power = (x + y + spin) / denominator
            rr.power = (y - x - spin) / denominator
            fl.power = (y - x + spin) / denominator
        }
    }
}