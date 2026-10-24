package org.firstinspires.ftc.teamcode.helpers

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap

@Configurable
class Drivetrain(hardwareMap: HardwareMap) {
    companion object {
        @JvmField
        val frReverse = true

        @JvmField
        val flReverse = false

        @JvmField
        val rrReverse = true

        @JvmField
        val rlReverse = false
    }

    val fr = hardwareMap.get("frontRight") as DcMotor
    val fl = hardwareMap.get("frontLeft") as DcMotor
    val rr = hardwareMap.get("rearRight") as DcMotor
    val rl = hardwareMap.get("rearLeft") as DcMotor


    init {
        fr.direction =
            if (frReverse) DcMotorSimple.Direction.FORWARD else DcMotorSimple.Direction.REVERSE
        fl.direction =
            if (flReverse) DcMotorSimple.Direction.FORWARD else DcMotorSimple.Direction.REVERSE
        rr.direction =
            if (rrReverse) DcMotorSimple.Direction.FORWARD else DcMotorSimple.Direction.REVERSE
        rl.direction =
            if (rlReverse) DcMotorSimple.Direction.FORWARD else DcMotorSimple.Direction.REVERSE
    }
}