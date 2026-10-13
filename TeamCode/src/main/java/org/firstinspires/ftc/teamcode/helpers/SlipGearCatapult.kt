package org.firstinspires.ftc.teamcode.helpers

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap

@Configurable
class SlipGearCatapult {
    companion object {
        @JvmField
        val preparePosition = 2090

        @JvmField
        val firePosition = 697

        @JvmField
        val reverse = false
    }

    private val motor: DcMotor

    constructor(hardwareMap: HardwareMap) {
        motor = hardwareMap.get("catapult") as DcMotor
        if (reverse) {
            motor.direction = DcMotorSimple.Direction.REVERSE
        }
        motor.mode = DcMotor.RunMode.RUN_TO_POSITION
        motor.power = 1.0
    }

    private var ready = false

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