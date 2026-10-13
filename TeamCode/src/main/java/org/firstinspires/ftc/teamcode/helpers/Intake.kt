package org.firstinspires.ftc.teamcode.helpers

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap

@Configurable
class Intake {
    companion object {
        @JvmField
        val power = 1.0

        @JvmField
        val reverse = false
    }

    private val motor: DcMotor
    var on = false
        set(value) {
            motor.power = if (value) {
                power
            } else {
                0.0
            }
            field = value
        }

    constructor(hardwareMap: HardwareMap) {
        motor = hardwareMap.get("intake") as DcMotor
        if (reverse) {
            motor.direction = DcMotorSimple.Direction.REVERSE
        }
    }

    fun toggle() {
        on = !on
    }
}