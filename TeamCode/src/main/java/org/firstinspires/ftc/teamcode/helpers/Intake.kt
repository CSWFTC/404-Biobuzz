package org.firstinspires.ftc.teamcode.helpers

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap

@Configurable
class Intake(hardwareMap: HardwareMap) {
    companion object {
        @JvmField
        var power = 1.0

        @JvmField
        var reverse = false
    }

    private val motor = hardwareMap.get("intake") as DcMotor
    var on = false
        set(value) {
            motor.power = if (value) {
                power
            } else {
                0.0
            }
            field = value
        }

    init {
        if (reverse) {
            motor.direction = DcMotorSimple.Direction.REVERSE
        }
    }

    fun toggle() {
        on = !on
    }
}