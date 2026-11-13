package org.firstinspires.ftc.teamcode.helpers

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import kotlinx.coroutines.*

@Configurable
class Catapult(hardwareMap: HardwareMap) {
    companion object {
        @JvmField
        var ticksPerDeg = 1.0
        @JvmField
        var launchAngle = 45.0
        @JvmField
        var startAngle = -90.0
        @JvmField
        var dir1 = DcMotorSimple.Direction.REVERSE
        @JvmField
        var dir2 = DcMotorSimple.Direction.FORWARD
    }

    private val motor1 = hardwareMap.get("catapult1") as DcMotor
    private val motor2 = hardwareMap.get("catapult2") as DcMotor

    init {
        motor1.direction = dir1
        motor2.direction = dir2
    }
    private var active = false
    suspend fun fire(){
        if(active)
            return
        active = true
        motor1.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        motor2.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        motor1.power = 1
        motor2.power = 1
        while(((motor1.currentPosition / ticksPerDeg) + startAngle) < launchAngle)
            yield()
        motor1.targetPosition = 0
        motor2.targetPosition = 0
        motor1.mode = DcMotor.RunMode.RUN_TO_POSITION
        motor2.mode = DcMotor.RunMode.RUN_TO_POSITION
        while(motor1.currentPosition > 0)
            yield()
        motor1.power = 0
        motor2.power = 0
        motor1.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        motor2.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        active = false
    }
    fun reset(){
        if(active)
            return
        motor1.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motor2.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
    }
}