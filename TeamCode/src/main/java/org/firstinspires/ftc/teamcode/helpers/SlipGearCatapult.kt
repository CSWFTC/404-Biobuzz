package org.firstinspires.ftc.teamcode.helpers

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap

@Configurable
class SlipGearCatapult(hardwareMap: HardwareMap) {
    companion object {
        @JvmField
        var preparePosition = 2090

        @JvmField
        var firePosition = 697

        @JvmField
        var reverse = false
    }

    private val motor = hardwareMap.get("catapult") as DcMotor

    init {
        if (reverse) {
            motor.direction = DcMotorSimple.Direction.REVERSE
        }
        motor.mode = DcMotor.RunMode.RUN_TO_POSITION
        motor.power = 1.0
    }

    var ready = false

    fun prepare() {
        if (!ready) {
            motor.targetPosition += preparePosition
            ready = true
        }
    }

    fun fire(): Boolean {
        if (!ready || motor.currentPosition < motor.targetPosition) {
            return false
        }
        motor.targetPosition += firePosition
        ready = false
        return true
    }

}