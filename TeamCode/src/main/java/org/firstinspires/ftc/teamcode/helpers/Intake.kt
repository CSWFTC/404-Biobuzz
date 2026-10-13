package org.firstinspires.ftc.teamcode.helpers

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareMap

class Intake {
    val motor: DcMotor
    public var on: Boolean = false
        set(value) = if(value){motor.power = 1.0}else{motor.power = 0.0}

    constructor(hardwareMap: HardwareMap) {
        motor = hardwareMap.get("intake") as DcMotor
    }
    public fun toggle(){
        on = !on
    }
}