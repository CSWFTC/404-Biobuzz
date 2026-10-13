package org.firstinspires.ftc.teamcode.helpers

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap

class Intake {
    val motor: DcMotor
    val power = 1.0
    val reverse = false
    var on: Boolean = false
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