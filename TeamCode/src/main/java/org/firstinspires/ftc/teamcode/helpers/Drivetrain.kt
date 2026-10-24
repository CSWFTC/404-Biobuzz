package org.firstinspires.ftc.teamcode.helpers

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap

@Configurable
class Drivetrain(hardwareMap: HardwareMap) {
    companion object {
        @JvmField
        var frDirection = DcMotorSimple.Direction.REVERSE

        @JvmField
        var flDirection = DcMotorSimple.Direction.FORWARD

        @JvmField
        var rrDirection = DcMotorSimple.Direction.REVERSE

        @JvmField
        var rlDirection = DcMotorSimple.Direction.FORWARD
    }

    val fr = hardwareMap.get("frontRight") as DcMotor
    val fl = hardwareMap.get("frontLeft") as DcMotor
    val rr = hardwareMap.get("rearRight") as DcMotor
    val rl = hardwareMap.get("rearLeft") as DcMotor


    init {
        fr.direction = frDirection
        fl.direction = flDirection
        rr.direction = rrDirection
        rl.direction = rlDirection
    }
}